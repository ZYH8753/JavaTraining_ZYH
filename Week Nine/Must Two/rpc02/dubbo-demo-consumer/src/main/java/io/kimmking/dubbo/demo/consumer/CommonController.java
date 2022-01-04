package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @Autowired
    private TransferService transferService;

    @RequestMapping(value = "transfer")
    public String transfer(@RequestBody Order order) {
        transferService.transfer(order);
        return "ok";
    }
}
