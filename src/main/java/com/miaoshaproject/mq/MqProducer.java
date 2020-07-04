package com.miaoshaproject.mq;

import com.alibaba.fastjson.JSON;
import com.miaoshaproject.dao.StockLogDOMapper;
import com.miaoshaproject.dataobject.StockLogDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.OrderService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
public class MqProducer {

    private DefaultMQProducer producer;

    private TransactionMQProducer transactionMQProducer;

    @Autowired
    OrderService orderService;

    @Resource
    StockLogDOMapper stockLogDOMapper;

    //声明value注解，引入配置变量
    @Value("${mq.nameserver.addr}")
    private String nameAddr;

    @Value("${mq.topicname}")
    private String topicName;

    @PostConstruct
    public void init() throws MQClientException {
        //mq producer初始化
        producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr(nameAddr);
        producer.start();

        transactionMQProducer = new TransactionMQProducer("transaction_producer_group");
        transactionMQProducer.setNamesrvAddr(nameAddr);
        transactionMQProducer.start();

        transactionMQProducer.setTransactionListener(new TransactionListener() {
            //发送事务型消息，消息的类型是prepare,不会被consumer立即执行
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
                //真正要做的事，创建订单
                Integer itemId = (Integer) ((Map)arg).get("itemId");
                Integer userId = (Integer) ((Map)arg).get("userId");
                Integer promoId = (Integer) ((Map)arg).get("promoId");
                Integer amount = (Integer) ((Map)arg).get("amount");
                String stockLogId = (String) ((Map)arg).get("stockLogId");

                try {
                    //这里进行订单创建
                    orderService.createOrder(userId,itemId,promoId,amount,stockLogId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    //失败事务回滚,设置对应的stockLog为回滚状态
                    StockLogDO stockLogDO = stockLogDOMapper.selectByPrimaryKey(stockLogId);
                    stockLogDO.setStatus(3);
                    stockLogDOMapper.updateByPrimaryKeySelective(stockLogDO);
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                //根据是否扣减库存成功，来判断要返回COMMIT,ROLLBACK还是UNKNOW
                String jsonString = new String(msg.getBody());
                Map<String,Object> map = JSON.parseObject(jsonString);
                Integer itemId = (Integer) map.get("itemId");
                Integer amount = (Integer) map.get("amount");
                String stockLogId = (String) map.get("stockLogId");
                StockLogDO stockLogDO = stockLogDOMapper.selectByPrimaryKey(stockLogId);
                if(stockLogDO == null) {
                    return LocalTransactionState.UNKNOW;
                }
                if(stockLogDO.getStatus()==2){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else if(stockLogDO.getStatus()==1){
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });

    }

    //事务型同步库存扣减消息
    public boolean transactionAsyncReduceStock(Integer userId,Integer promoId,Integer itemId,Integer amount,String stockLogId) {
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("itemId",itemId);
        bodyMap.put("amount",amount);
        bodyMap.put("stockLogId",stockLogId);

        Map<String,Object> argsMap = new HashMap<>();
        argsMap.put("itemId",itemId);
        argsMap.put("amount",amount);
        argsMap.put("userId",userId);
        argsMap.put("promoId",promoId);
        argsMap.put("stockLogId",stockLogId);
        //投放消息
        Message message = new Message(topicName,"increase",JSON.toJSON(bodyMap).toString().getBytes(Charset.forName("UTF-8")));
        TransactionSendResult sendResult = null;
        try {
            //投递prepare消息
            sendResult = transactionMQProducer.sendMessageInTransaction(message,argsMap);
        } catch (MQClientException e) {
            e.printStackTrace();
            return false;
        }
        if(sendResult.getLocalTransactionState()==LocalTransactionState.ROLLBACK_MESSAGE) {
            return false;
        }else if(sendResult.getLocalTransactionState()==LocalTransactionState.COMMIT_MESSAGE){
            return true;
        }else {
            return false;
        }
    }

    //同步库存扣减消息
    public boolean asyncReduceStock(Integer itemId,Integer amount) {
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("itemId",itemId);
        bodyMap.put("amount",amount);
        //投放消息
        Message message = new Message(topicName,"increase",JSON.toJSON(bodyMap).toString().getBytes(Charset.forName("UTF-8")));
        try {
           producer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
            return false;
        } catch (RemotingException e) {
            e.printStackTrace();
            return false;
        } catch (MQBrokerException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
