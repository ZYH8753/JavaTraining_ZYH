package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Order;
import org.dromara.hmily.annotation.Hmily;

public interface TransferService {
    @Hmily
    void transfer(Order order);
}
