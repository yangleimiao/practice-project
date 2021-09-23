package com.example.mybatis.service.impl;

import com.example.mybatis.mapper.DataMapper;
import com.example.mybatis.model.ClassModel;
import com.example.mybatis.model.DataModel;
import com.example.mybatis.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanglei
 * @date 2021/9/22
 */
@Service
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    @Override
    public List<DataModel> list(int claId) throws Exception {
         return dataMapper.list(claId);

    }

    @Override
    public DataModel findByStuId(int stuId) {
        return dataMapper.selectStuById(stuId);

    }

    @Override
    public int insert(DataModel model) {
        return dataMapper.insert(model);
    }

    @Override
    public int delete(int stuId) {
        return dataMapper.delete(stuId);
    }

    @Override
    public ClassModel fetchStudentsOfClass(int claId) {
        return dataMapper.fetchStudentsOfClass(claId);
        //return null;
    }

    @Override
    public int updateByStuId(DataModel model) {
        return dataMapper.updateByStuId(model);
        //return 0;
    }


}
