package com.mage.crm.query;

import com.mage.crm.base.BaseQuery;

public class CustomerGCQuery extends BaseQuery {

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
