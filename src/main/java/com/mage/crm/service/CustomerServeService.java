package com.mage.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mage.crm.dao.CustomerServeDao;
import com.mage.crm.dto.ServeTypeDto;
import com.mage.crm.query.CustomerServeQuery;
import com.mage.crm.util.AssertUtil;
import com.mage.crm.util.CookieUtil;
import com.mage.crm.vo.CustomerServe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerServeService {
    @Resource
    private CustomerServeDao customerServeDao;
    /**
     * 必要性的检测
     * 1.服务类型
     * 2.客户
     * 3.服务请求
     * 额外数据的补录
     *  1.创建时间
     *  2.更新时间
     *  3.isvalid = 1
     *  4.state = 1   1- 5 新创建 1、已分配 2、已处理3 、已反馈4、已归档 5
     *
     * @param customerServe
     */
    public void insert(CustomerServe customerServe) {
        cheakCustomerServeParams(customerServe.getServeType(),customerServe.getCustomer(),customerServe.getServiceRequest());
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());
        customerServe.setIsValid(1);
        customerServe.setState("1");
        AssertUtil.isTrue(customerServeDao.insert(customerServe)<1,"服务创建失败");
    }

    private void cheakCustomerServeParams(String serveType, String customer,String serviceRequest) {
        AssertUtil.isTrue(StringUtils.isBlank(serveType), "服务类型非空!");
        AssertUtil.isTrue(StringUtils.isBlank(customer), "客户名称非空!");
        AssertUtil.isTrue(StringUtils.isBlank(serviceRequest), "内容非空!");
    }

    public Map<String,Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery) {
        PageHelper.startPage(customerServeQuery.getPage(),customerServeQuery.getRows());
        List<CustomerServe> list = customerServeDao.queryCustomerServesByParams(customerServeQuery.getState());
        PageInfo<CustomerServe> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    public void update(CustomerServe customerServe, HttpServletRequest httpServletRequest) {
        customerServe.setUpdateDate(new Date());
        if(customerServe.getState().equals("2")){
            customerServe.setAssigner(CookieUtil.getCookieValue(httpServletRequest,"trueName"));
            customerServe.setAssignTime(new Date());
        }else if(customerServe.getState().equals("3")){
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProce()),"处理内容不能为空！");
            customerServe.setServiceProceTime(new Date());
        }else if(customerServe.getState().equals("4")){
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProceResult()),"处理结果不能为空！");
            AssertUtil.isTrue(StringUtils.isBlank(customerServe.getMyd()),"满意度不能为空！");
            customerServe.setState("5");
        }
        AssertUtil.isTrue(customerServeDao.update(customerServe)<1,"操作失败！");
    }

    public Map<String,Object> queryCustomerServeType() {
        List<ServeTypeDto> typeDtoList = customerServeDao.queryCustomerServeType();
        String[] types = null;
        ServeTypeDto[] datas = null;
        Map<String, Object> map = new HashMap<>();
        map.put("code",300);
        if(typeDtoList!=null&&typeDtoList.size()>0){
            types = new String[typeDtoList.size()];
            datas = new ServeTypeDto[typeDtoList.size()];
            for(int i = 0;i<typeDtoList.size();i++){
                types[i] = typeDtoList.get(i).getName();
                datas[i] = typeDtoList.get(i);
            }
            map.put("code",200);
            map.put("types",types);
            map.put("datas",datas);
        }
        return map;
    }
}
