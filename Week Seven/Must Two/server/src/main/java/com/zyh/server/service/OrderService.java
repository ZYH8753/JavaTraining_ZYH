package com.zyh.server.service;

import com.zyh.server.datasource.DataSourceAnnotation;
import com.zyh.server.entity.Order;
import com.zyh.server.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    @DataSourceAnnotation(name= DataSourceAnnotation.master)
    public void insertMaster(Order order) {
        orderMapper.insert(order);
    }

    @DataSourceAnnotation(name= DataSourceAnnotation.slave)
    public void insertSlave(Order order) {
        orderMapper.insert(order);
    }
}
