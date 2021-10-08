package com.example.mybatis.service;

import com.example.mybatis.model.DataModel;

/**
 * @author yanglei
 * @date 2021/9/23
 */
public interface DynamicSQLService {
    DataModel getStuByIf(int stuId);
    DataModel getStuByIf2(DataModel model);
    DataModel getStuByChoose(DataModel model);
}
