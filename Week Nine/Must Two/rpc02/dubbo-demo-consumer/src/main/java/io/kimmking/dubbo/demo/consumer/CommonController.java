package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {
//    @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
//    private UserService userService;
//
//    @DubboReference(version = "1.0.0")
//    private DollarAccountService dollarAccountService;
//
//    @DubboReference(version = "1.0.0")
//    private RmbAccountService rmbAccountService;

    @RequestMapping(value = "transfer", method = RequestMethod.GET)
    public String transfer(@RequestBody Order order) {
//    public String transfer(@RequestBody Order order) {
        System.out.println("get");
//        int ans = dollarAccountService.freezePrice(order.getDollarId(), order.getDollarPrice());
//        System.out.println(ans);
        return "ok";
    }
}
