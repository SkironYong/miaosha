package com.miaoshaproject.error;

public class BusinessException extends Exception implements CommonError{

    //commonError其实就是EmBusinessError类
    private CommonError commonError;

    //构造函数
    //直接接收EmBusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError) {
        //调用父类初始化
        super();
        this.commonError = commonError;
    }

    //接收自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }
    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
