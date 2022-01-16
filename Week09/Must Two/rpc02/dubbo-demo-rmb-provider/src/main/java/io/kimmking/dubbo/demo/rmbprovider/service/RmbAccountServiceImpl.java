package io.kimmking.dubbo.demo.rmbprovider.service;

import com.alibaba.fastjson.JSON;
import io.kimmking.dubbo.demo.api.FreezeAccount;
import io.kimmking.dubbo.demo.api.Order;
import io.kimmking.dubbo.demo.api.RmbAccountService;
import io.kimmking.dubbo.demo.rmbprovider.entity.RmbAccount;
import io.kimmking.dubbo.demo.rmbprovider.mapper.FreezeMapper;
import io.kimmking.dubbo.demo.rmbprovider.mapper.RmbAccountMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService//(version = "1.0.0", tag = "red", weight = 100)
public class RmbAccountServiceImpl implements RmbAccountService {
    @Autowired
    RmbAccountMapper rmbAccountMapper;

    @Autowired
    FreezeMapper freezeMapper;

    @Override
    public FreezeAccount freezePrice(long rmbId, int price) {
        RmbAccount rmbAccount = rmbAccountMapper.selectByUserid(rmbId);
        if (rmbAccount == null) {
            throw new RuntimeException("rmb 账户不存在！");
        }

        int ans = rmbAccountMapper.subPriceById(rmbAccount.getId(), price);
        if (ans != 1) {
            throw new RuntimeException("rmb 账户余额不足！");
        }
        FreezeAccount freezeAccount = new FreezeAccount(rmbAccount.getUserid(), 0, price);
        int insertAns = freezeMapper.insert(freezeAccount);
        System.out.println("insertAns:" + insertAns);
        System.out.println("freezeAccount:" + freezeAccount);
        return freezeAccount;
    }

    @Override
    @Transactional
    public Integer addRmbPrice(FreezeAccount freezeAccount, long targetUseId) {
        int update = rmbAccountMapper.updatePriceInt(targetUseId, freezeAccount.getRmbPrice());
        int delete = freezeMapper.deleteById(freezeAccount.getId());
        System.out.println("addRmbPrice :" + (update + delete));
        return update + delete;
    }

    @Override
    public FreezeAccount prepare(BusinessActionContext actionContext, Order order) {
        RmbAccount rmbAccount = rmbAccountMapper.selectByUserid(order.getRmbId());
        if (rmbAccount == null) {
            throw new RuntimeException("rmb 账户不存在！");
        }

        int ans = rmbAccountMapper.subPriceById(rmbAccount.getId(), order.getRmbPrice());
        if (ans != 1) {
            throw new RuntimeException("rmb 账户余额不足！");
        }
        FreezeAccount freezeAccount = new FreezeAccount(rmbAccount.getUserid(), 0, order.getRmbPrice());
//        int insertAns = freezeMapper.insert(freezeAccount);
//        System.out.println("insertAns:" + insertAns);
        System.out.println("freezeAccount:" + freezeAccount);
        return freezeAccount;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        Order order = JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("order")), Order.class);
        int update = rmbAccountMapper.updatePriceInt(order.getDollarId(), order.getRmbPrice());
//        int delete = freezeMapper.deleteById(freezeAccount.getId());
        System.out.println("commit:xid=" + actionContext.getXid());
        System.out.println("addDollarPrice :" + (update));
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext actionContext) {
        Order order = JSON.parseObject(JSON.toJSONString(actionContext.getActionContext("order")), Order.class);
        Integer dollarAns = rmbAccountMapper.updatePriceInt(order.getRmbId(), order.getRmbPrice());
//        int delete = freezeMapper.deleteById(freezeAccount.getId());
        System.out.println("cancelTransfer dollarAns:"+dollarAns);
        return true;
    }

    @Override
    public boolean rmbTransfer(Order order) {
        RmbAccount rmbAccount = rmbAccountMapper.selectByUserid(order.getRmbId());
        if (rmbAccount == null) {
            throw new RuntimeException("rmb 账户不存在！");
        }

        int ans = rmbAccountMapper.subPriceById(rmbAccount.getId(), order.getRmbPrice());
        if (ans != 1) {
            throw new RuntimeException("rmb 账户余额不足！");
        }
        FreezeAccount freezeAccount = new FreezeAccount(rmbAccount.getUserid(), 0, order.getRmbPrice());
        int insertAns = freezeMapper.insert(freezeAccount);
        System.out.println("insertAns:" + insertAns);
        System.out.println("freezeAccount:" + freezeAccount);

        int update = rmbAccountMapper.updatePriceInt(order.getDollarId(), order.getRmbPrice());
        int delete = freezeMapper.deleteById(freezeAccount.getId());
        System.out.println("addDollarPrice :" + (update + delete));
        return true;
    }
}
