package com.zyh.server.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    public List<Order> selectAll() {
        List<Order> list = orderMapper.selectList(new QueryWrapper<Order>());
        System.out.println(list);
        return list;
    }

    public int updateUserIdByOrderId(String orderId, int userId) {
        return orderMapper.updateUserIdByOrderId(orderId, userId);
    }

    public int deleteOrder(String orderId) {
        return orderMapper.delete(new UpdateWrapper<Order>().eq("order_id", orderId));
    }


}
