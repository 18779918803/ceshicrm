package com.mage.crm.service;

import com.mage.crm.dao.DataDicDao;
import com.mage.crm.vo.DataDic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDicService {
    @Resource
    private DataDicDao dataDicDao;

    public List<DataDic> queryDataDicValueByDataDicName(String dataDicName) {
       return dataDicDao.queryDataDicValueByDataDicName(dataDicName);
    }
}
