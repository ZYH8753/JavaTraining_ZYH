package com.zyh.server.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyh.server.entity.Order;
import com.zyh.server.mapper.OrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public void insertOrders() {
        for (int i=0; i<32; i++) {
            orderMapper.insert(new Order(null, String.valueOf(i), 2));
        }
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void addOrdersWithError() {
        for (int i=0; i<32; i++) {
            orderMapper.insert(new Order(null, String.valueOf(i), 3));
        }
        int a = 1/0;
        orderMapper.insert(new Order(null, String.valueOf(0), 3));
    }



}
