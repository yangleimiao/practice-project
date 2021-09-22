package com.example.mybatis.service;

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
    DataModel findByStuId(int stuId);
}
