package com.example.mybatis.service;

import com.example.mybatis.model.ClassModel;
import com.example.mybatis.model.DataModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yanglei
 * @date 2021/9/22
 */
@Service
public interface DataService {
    List<DataModel> list(int claId) throws Exception;
    DataModel findByStuId(int stuId);
    int insert(DataModel model);
    int delete(int stuId);
    ClassModel fetchStudentsOfClass(int claId);
    int updateByStuId(DataModel model);
}
