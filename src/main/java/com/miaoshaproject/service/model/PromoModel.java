package com.miaoshaproject.service.model;

import org.joda.time.DateTime;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//秒杀 营销模型
public class PromoModel implements Serializable{
    private Integer id;

    //秒杀活动状态  1表示还未开始 2 表示进行中 3表示已结束
    private Integer status;

    //秒杀活动名称
    private String promoName;

    //秒杀活动的开始时间
    private DateTime startDate;

    //秒杀活动的结束时间
    private DateTime endDate;

    //秒杀活动的适用商品
    private Integer itemId;

    //秒杀活动的商品价格
    private BigDecimal promoItemPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
