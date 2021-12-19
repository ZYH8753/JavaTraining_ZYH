package com.zyh.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zyh.server.datasource.DataSourceAnnotation;
import com.zyh.server.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.zyh.server")
public class DataSourceConfig {
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean
    public DataSource slaveDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource(@Autowired DataSource masterDataSource, @Autowired DataSource slaveDataSource) {
        Map<Object, Object> map = new HashMap<>();
        map.put(DataSourceAnnotation.master, masterDataSource);
        map.put(DataSourceAnnotation.slave, slaveDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }
}
