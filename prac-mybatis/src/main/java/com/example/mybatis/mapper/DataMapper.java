package com.example.mybatis.mapper;

import com.example.mybatis.model.ClassModel;
import com.example.mybatis.model.DataModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author yanglei
 * @date 2021/9/22
 */
@Mapper
public interface DataMapper {
    List<DataModel> list(int claId)  throws Exception;
    DataModel selectStuById(int stuId);

    int insert(DataModel model);
    int delete(int stuId);
    ClassModel fetchStudentsOfClass(int claId);

    int updateByStuId(DataModel model);
}
