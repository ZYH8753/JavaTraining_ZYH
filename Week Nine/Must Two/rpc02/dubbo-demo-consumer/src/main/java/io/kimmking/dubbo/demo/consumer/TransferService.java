package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Order;
import org.dromara.hmily.annotation.Hmily;

public interface TransferService {
    @Hmily
    void transferByHmilyTCC(Order order);

    void transferBySeataTCC(Order order);

    void transferBySeataAT(Order order);
}
