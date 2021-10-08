package com.example.mybatis.mapper;

import com.example.mybatis.model.DataModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yanglei
 * @date 2021/9/23
 */
@Mapper
public interface DynamicSQLMapper {
    DataModel getStuByIf(int stuId);
    DataModel getStuByIf2(DataModel model);
    DataModel getStuByChoose(DataModel model);
}
