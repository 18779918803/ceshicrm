package com.mage.crm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mage.crm.base.BaseVo;

import java.util.Date;

public class CustomerLoss extends BaseVo{
    private Integer id;
    private String cusNo;
    private String cusName;
    private String cusManager;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date lastOrderTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date confirmLossTime;
    private Integer state;
    private String lossReason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusManager() {
        return cusManager;
    }

    public void setCusManager(String cusManager) {
        this.cusManager = cusManager;
    }

    public Date getLassOrderTime() {
        return lastOrderTime;
    }

    public void setLassOrderTime(Date lassOrderTime) {
        this.lastOrderTime = lassOrderTime;
    }

    public Date getConfirmLossTime() {
        return confirmLossTime;
    }

    public void setConfirmLossTime(Date confirmLossTime) {
        this.confirmLossTime = confirmLossTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLossReason() {
        return lossReason;
    }

    public void setLossReason(String lossReason) {
        this.lossReason = lossReason;
    }
}
