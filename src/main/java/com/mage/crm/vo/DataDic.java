package com.mage.crm.vo;

import com.mage.crm.base.BaseVo;

public class DataDic extends BaseVo {
    private String id;
    private String dateDicName;
    private String dataDicValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateDicName() {
        return dateDicName;
    }

    public void setDateDicName(String dateDicName) {
        this.dateDicName = dateDicName;
    }

    public String getDataDicValue() {
        return dataDicValue;
    }

    public void setDataDicValue(String dataDicValue) {
        this.dataDicValue = dataDicValue;
    }
}
