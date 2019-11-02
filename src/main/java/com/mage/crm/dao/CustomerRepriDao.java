package com.mage.crm.dao;

import com.mage.crm.vo.CustomerReprieve;

import java.util.List;

public interface CustomerRepriDao {
    List<CustomerReprieve> customerReprieveByLossId(String lossId);

    int insertReprive(CustomerReprieve customerReprieve);

    int updateReprive(CustomerReprieve customerReprieve);

    int delete(Integer id);
}
