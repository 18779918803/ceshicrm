package com.mage.crm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.CustomerLossDao;
import com.mage.crm.dao.CustomerRepriDao;
import com.mage.crm.query.CustomerRepriQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.vo.CustomerReprieve;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerRepriService {
    @Resource
    private CustomerRepriDao customerRepriDao;
    @Resource
    private CustomerLossService customerLossService;

    public Map<String,Object> customerReprieveByLossId(CustomerRepriQuery customerRepriQuery) {
        PageHelper.startPage(customerRepriQuery.getPage(),customerRepriQuery.getRows());
        List<CustomerReprieve> list = customerRepriDao.customerReprieveByLossId(customerRepriQuery.getLossId());
        PageInfo<CustomerReprieve> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    public void insertReprive(CustomerReprieve customerReprieve) {
        checkParams(customerReprieve.getLossId(),customerReprieve.getMeasure());
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());
        customerReprieve.setIsValid(1);
        AssertUtil.isTrue(customerRepriDao.insertReprive(customerReprieve)<1,"添加暂缓措施失败！");
    }

    public void checkParams(Integer lossId,String measure){
        AssertUtil.isTrue(StringUtils.isBlank(measure),"暂缓措施不能为空");
        Map<String,Object> map = customerLossService.queryCustomerLossesById(lossId);
        AssertUtil.isTrue(lossId==null||map==null||map.isEmpty(),"流失记录不存在");
    }

    public void updateReprive(CustomerReprieve customerReprieve) {
        checkParams(customerReprieve.getLossId(),customerReprieve.getMeasure());
        customerReprieve.setUpdateDate(new Date());
        AssertUtil.isTrue(customerRepriDao.updateReprive(customerReprieve)<1,"修改暂缓措施失败");
    }

    public void delete(Integer id) {
        AssertUtil.isTrue(customerRepriDao.delete(id)<1,"修改暂缓措施失败");
    }
}
