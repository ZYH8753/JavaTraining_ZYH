package com.zyh.server.controller;

import com.zyh.server.entity.Order;
import com.zyh.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/master")
    public String master() {
        orderService.insertMaster(new Order(null,"1",1));
        return "ok";
    }

    @RequestMapping("/slave")
    public String slave() {
        int size = orderService.select();
        return String.valueOf(size);
    }
}
