package com.example.mybatis.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author yanglei
 * @date 2021/9/22
 */
@Data
public class DataModel {

    private int stuId;

    private String stuName;

    private int stuClaId;

    public String toString(){
        return "["+stuId+","+stuName+","+stuClaId+"]";
    }

}
