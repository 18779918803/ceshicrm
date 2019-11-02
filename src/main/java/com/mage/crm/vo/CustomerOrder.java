package com.mage.crm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mage.crm.base.BaseVo;

import java.util.Date;

public class CustomerOrder extends BaseVo{
    private Integer id;

    private Integer cusId;

    private String orderNo;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderDate;

    private String address;

    private Integer state;

    private Integer isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public Integer getIsValid() {
        return isValid;
    }

    @Override
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
