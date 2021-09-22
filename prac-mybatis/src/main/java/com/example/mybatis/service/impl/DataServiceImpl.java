package com.example.mybatis.service.impl;

import com.example.mybatis.mapper.DataMapper;
import com.example.mybatis.model.DataModel;
import com.example.mybatis.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yanglei
 * @date 2021/9/22
 */
@Service
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    @Override
    public DataModel findByStuId(int stuId) {
        return dataMapper.selectStuById(stuId);

    }


}
