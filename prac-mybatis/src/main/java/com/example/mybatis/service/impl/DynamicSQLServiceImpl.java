package com.example.mybatis.service.impl;

import com.example.mybatis.mapper.DynamicSQLMapper;
import com.example.mybatis.model.DataModel;
import com.example.mybatis.service.DynamicSQLService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yanglei
 * @date 2021/9/23
 */
@Service
public class DynamicSQLServiceImpl implements DynamicSQLService {
    @Resource
    DynamicSQLMapper mapper;
    @Override
    public DataModel getStuByIf(int stuId) {
        return mapper.getStuByIf(stuId);
        //return null;
    }
}
