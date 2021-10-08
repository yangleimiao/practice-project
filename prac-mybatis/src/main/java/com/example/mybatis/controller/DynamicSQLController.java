package com.example.mybatis.controller;

import com.example.mybatis.model.DataModel;
import com.example.mybatis.service.DynamicSQLService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * @author yanglei
 * @date 2021/9/23
 */
@RestController
@RequestMapping(value = "/dynamic")
public class DynamicSQLController {
    @Resource
    DynamicSQLService service;
    

    @GetMapping(value = "/getStuByIf")
    @ResponseBody
    public DataModel getStuByIf(@PathParam(value = "stuId") int stuId){
        return service.getStuByIf(stuId);
    }

    @GetMapping(value = "/getStuByIf2")
    @ResponseBody
    public DataModel getStuByIf2(@PathParam(value = "stuName") String stuName){
        DataModel model = new DataModel();
        // model.setStuId(stuId);
        model.setStuName(stuName);
        //System.out.println(model.toString());
        return service.getStuByIf2(model);
    }

    @GetMapping(value = "/getStuByChoose")
    @ResponseBody
    public DataModel getStuByChoose(
            @PathParam(value = "stuName") String stuName
            //@PathParam(value = "stuId") int stuId
    ){
        DataModel model = new DataModel();
        //model.setStuId(stuId);
        model.setStuName(stuName);
        return service.getStuByChoose(model);
    }
}
