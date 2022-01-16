package com.zyh.server.controller;

import com.zyh.server.entity.Order;
import com.zyh.server.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.apache.shardingsphere.core.strategy.route.ShardingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrders")
    public String addOrder() {
        orderService.insertOrders();
        return "ok";
    }

    @RequestMapping("/addOrdersWithError")
    public String addOrdersWithError() {
        orderService.addOrdersWithError();
        return "ok";
    }


}
