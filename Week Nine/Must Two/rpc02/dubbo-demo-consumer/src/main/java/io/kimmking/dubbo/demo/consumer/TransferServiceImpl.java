package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.DollarAccountService;
import io.kimmking.dubbo.demo.api.FreezeAccount;
import io.kimmking.dubbo.demo.api.Order;
import io.kimmking.dubbo.demo.api.RmbAccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService{

    @DubboReference
    private DollarAccountService dollarAccountService;

    @DubboReference
    private RmbAccountService rmbAccountService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @HmilyTCC(confirmMethod = "confirmTransfer", cancelMethod = "cancelTransfer")
    public void transfer(Order order) {
        System.out.println("transfer start,order:" + order);
		FreezeAccount dollarFreezeAccount = dollarAccountService.freezePrice(order.getDollarId(), order.getDollarPrice());
		FreezeAccount rmbFreezeAccount = rmbAccountService.freezePrice(order.getRmbId(), order.getRmbPrice());
		System.out.println("dollarFreezeAccount : " + dollarFreezeAccount);
		System.out.println("rmbFreezeAccount : " + rmbFreezeAccount);
		order.setDollarFreezeAccount(dollarFreezeAccount);
		order.setRmbFreezeAccount(rmbFreezeAccount);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirmTransfer(Order order) {
        Integer dollarAns = dollarAccountService.addDollarPrice(order.getDollarFreezeAccount(), order.getRmbId());
        Integer rmbAns = rmbAccountService.addRmbPrice(order.getRmbFreezeAccount(), order.getDollarId());
        System.out.println("confirmTransfer dollarAns:"+dollarAns);
        System.out.println("confirmTransfer rmbAns:"+rmbAns);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancelTransfer(Order order) {
        Integer dollarAns = dollarAccountService.addDollarPrice(order.getDollarFreezeAccount(), order.getDollarId());
        Integer rmbAns = rmbAccountService.addRmbPrice(order.getRmbFreezeAccount(), order.getRmbId());
        System.out.println("cancelTransfer dollarAns:"+dollarAns);
        System.out.println("cancelTransfer rmbAns:"+rmbAns);
        return true;
    }
}
