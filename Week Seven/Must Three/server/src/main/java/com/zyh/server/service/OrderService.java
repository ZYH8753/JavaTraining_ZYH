package com.zyh.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyh.server.entity.Order;
import com.zyh.server.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public void insertMaster(Order order) {
        orderMapper.insert(order);
    }

    public int select() {
        List<Order> list = orderMapper.selectList(new QueryWrapper<Order>().gt("id",0));
        System.out.println(list);
        return list.size();
    }


}
