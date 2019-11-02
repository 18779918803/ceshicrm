package com.mage.crm.query;

import com.mage.crm.base.BaseQuery;

public class CustomerQuery extends BaseQuery {
    private String khno;
    private String name;

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
