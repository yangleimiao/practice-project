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
        DataModel model = mapper.getStuByIf(stuId);
        System.out.println(model.toString());
        return model;
        //return null;
    }

    @Override
    public DataModel getStuByIf2(DataModel model) {
        System.out.println("--------");
        DataModel stu = mapper.getStuByIf2(model);
        System.out.println("-----: "+stu.getStuName());
        return stu;
        //return null;
    }

    @Override
    public DataModel getStuByChoose(DataModel model) {
        return mapper.getStuByChoose(model);
        //return null;
    }
}
