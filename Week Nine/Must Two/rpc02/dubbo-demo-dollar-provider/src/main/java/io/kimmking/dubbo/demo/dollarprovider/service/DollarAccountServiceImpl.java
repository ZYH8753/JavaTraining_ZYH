package io.kimmking.dubbo.demo.dollarprovider.service;

import io.kimmking.dubbo.demo.api.DollarAccountService;
import io.kimmking.dubbo.demo.api.FreezeAccount;
import io.kimmking.dubbo.demo.dollarprovider.entity.DollarAccount;
import io.kimmking.dubbo.demo.dollarprovider.mapper.DollarAccountMapper;
import io.kimmking.dubbo.demo.dollarprovider.mapper.FreezeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService//(version = "1.0.0", tag = "red", weight = 100)
public class DollarAccountServiceImpl implements DollarAccountService {
    @Autowired
    DollarAccountMapper dollarAccountMapper;

    @Autowired
    FreezeMapper freezeMapper;


    @Override
    public FreezeAccount freezePrice(long dollarId, int price) {
        DollarAccount dollarAccount = dollarAccountMapper.selectByUserid(dollarId);
        if (dollarAccount == null) {
            throw new RuntimeException("dollar 账户不存在！");
        }

        int ans = dollarAccountMapper.subPriceById(dollarAccount.getId(), price);
        if (ans != 1) {
            throw new RuntimeException("dollar 账户余额不足！");
        }
        FreezeAccount freezeAccount = new FreezeAccount(dollarAccount.getUserid(), price, 0);
        int insertAns = freezeMapper.insert(freezeAccount);
        System.out.println("insertAns:" + insertAns);
        System.out.println("freezeAccount:" + freezeAccount);
        return freezeAccount;
    }

    @Override
    @Transactional
    public Integer addDollarPrice(FreezeAccount freezeAccount, long targetUseId) {
        int update = dollarAccountMapper.updatePriceInt(targetUseId, freezeAccount.getDollarPrice());
        int delete = freezeMapper.deleteById(freezeAccount.getId());
        System.out.println("addDollarPrice :" + (update + delete));
        return update + delete;
    }
}
