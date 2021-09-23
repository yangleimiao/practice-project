package com.example.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatis.model.ClassModel;
import com.example.mybatis.model.DataModel;
import com.example.mybatis.service.DataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanglei
 * @date 2021/9/22
 */
@RestController
public class DataController {
    @Resource
    private DataService dataService;
    @GetMapping(value = "/findByStuId")
    public DataModel findByStuId(@RequestParam(value = "stuId") int stuId){
        return dataService.findByStuId(stuId);
    }

    @GetMapping(value = "/list")
    public List<DataModel> list(@RequestParam(value = "claId") int claId) throws Exception {
        return dataService.list(claId);
    }
    @PostMapping("/insert")
    @ResponseBody
    public int insert(@RequestBody JSONObject jsonObject){
        int stuId = Integer.parseInt(jsonObject.get("stuId").toString());
        String stuName = jsonObject.getString("stuName");
        int claId = Integer.parseInt(jsonObject.getString("claId"));

        DataModel model = new DataModel();
        model.setStuId(stuId);
        model.setStuName(stuName);
        model.setStuClaId(claId);
        return dataService.insert(model);
    }
    //@GetMapping(value = "/delete")
    @DeleteMapping(value = "/delete/{stuId}")
    public int delete(@PathVariable int stuId){
        return dataService.delete(stuId);
    }
    @GetMapping(value = "/fetch")
    public ClassModel fetchByClass(int claId){
        return dataService.fetchStudentsOfClass(claId);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public int updateByStuId(@RequestBody JSONObject jsonObject){
        int stuId = Integer.parseInt(jsonObject.get("stuId").toString());
        String stuName = jsonObject.getString("stuName");
        int claId = Integer.parseInt(jsonObject.getString("claId"));

        DataModel model = new DataModel();
        model.setStuId(stuId);
        model.setStuName(stuName);
        model.setStuClaId(claId);
        return dataService.updateByStuId(model);
    }
}
