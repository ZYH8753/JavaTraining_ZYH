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

    @RequestMapping("/addOrder")
    public String addOrder() {
        for (int i=0; i<32; i++) {
            orderService.insertMaster(new Order(null, String.valueOf(i), 1));
        }
        return "ok";
    }

    @RequestMapping("/getOrder")
    public String getOrder() {
        List<Order> orderList = orderService.selectAll();
        System.out.println(orderList.size());
        return "ok";
    }

    @RequestMapping("/updateOrder")
    public String updateOrder() {
        int ans = orderService.updateUserIdByOrderId("0", 123);
        System.out.println(ans);
        return String.valueOf(ans);
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder() {
        int ans = orderService.deleteOrder("0");
        System.out.println(ans);
        return String.valueOf(ans);
    }

}
