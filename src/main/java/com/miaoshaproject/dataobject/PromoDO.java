package com.miaoshaproject.dataobject;

import java.util.Date;

public class PromoDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.id
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.promo_name
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    private String promoName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.start_time
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    private Date startDate;

    private Date endDate;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.item_id
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    private Integer itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column promo.promo_item_price
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    private Double promoItemPrice;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.id
     *
     * @return the value of promo.id
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.id
     *
     * @param id the value for promo.id
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.promo_name
     *
     * @return the value of promo.promo_name
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public String getPromoName() {
        return promoName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.promo_name
     *
     * @param promoName the value for promo.promo_name
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public void setPromoName(String promoName) {
        this.promoName = promoName == null ? null : promoName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.start_time
     *
     * @return the value of promo.start_time
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.start_time
     *
     * @param startDate the value for promo.start_time
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.item_id
     *
     * @return the value of promo.item_id
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */


    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.item_id
     *
     * @param itemId the value for promo.item_id
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column promo.promo_item_price
     *
     * @return the value of promo.promo_item_price
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public Double getPromoItemPrice() {
        return promoItemPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column promo.promo_item_price
     *
     * @param promoItemPrice the value for promo.promo_item_price
     *
     * @mbg.generated Sun Mar 08 23:25:00 GMT+08:00 2020
     */
    public void setPromoItemPrice(Double promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

}