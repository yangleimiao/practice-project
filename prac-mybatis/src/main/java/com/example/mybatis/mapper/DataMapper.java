package com.example.mybatis.mapper;

import com.example.mybatis.model.DataModel;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author yanglei
 * @date 2021/9/22
 */
@Mapper
public interface DataMapper {
    DataModel list(int claId);
    DataModel selectStuById(int stuId);
}
