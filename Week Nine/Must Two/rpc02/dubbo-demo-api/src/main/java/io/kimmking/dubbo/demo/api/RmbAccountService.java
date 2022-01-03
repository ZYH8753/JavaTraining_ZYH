package io.kimmking.dubbo.demo.api;

public interface RmbAccountService {
    FreezeAccount freezePrice(long dollarId, int price);

    Integer addRmbPrice(FreezeAccount freezeAccount, long targetUseId);
}
