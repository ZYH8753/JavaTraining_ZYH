package io.kimmking.dubbo.demo.api;

public interface DollarAccountService {
    FreezeAccount freezePrice(long dollarId, int price);

    Integer addDollarPrice(FreezeAccount freezeAccount, long targetUseId);
}
