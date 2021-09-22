package com.example.mybatis.controller;

import com.example.mybatis.model.DataModel;
import com.example.mybatis.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author yanglei
 * @date 2021/9/22
 */
@RestController
public class DataController {
    @Resource
    private DataService dataService;
    @GetMapping(value = "/list")
    public DataModel list(@RequestParam(value = "stuId") int stuId){
        return dataService.findByStuId(stuId);
    }

}
