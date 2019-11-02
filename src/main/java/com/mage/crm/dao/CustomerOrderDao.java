package com.mage.crm.dao;

import com.mage.crm.query.CustomerOrderQuery;
import com.mage.crm.vo.CustomerOrder;

import java.util.List;
import java.util.Map;

public interface CustomerOrderDao {

   List<CustomerOrder> queryOrdersByCid(CustomerOrderQuery customerOrderQuery);

    Map<String,Object> queryOrderInfoById(Integer orderId);
}
