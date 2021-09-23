package com.example.mybatis.model;

import lombok.Data;

import java.util.List;

/**
 * @author yanglei
 * @date 2021/9/23
 */
@Data
public class ClassModel {
    private int claId;
    private List<DataModel> students;
}
