package com.example.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author yanglei
 * @date 2021/9/22
 */
@Configuration
public class DruidConfig {
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidPrimary(){
        return new DruidDataSource();
    }
}
