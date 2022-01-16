package io.kimmking.dubbo.demo.api;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

public interface DollarAccountService {
    // just for HmilyTCC
    FreezeAccount freezePrice(long dollarId, int price);
    Integer addDollarPrice(FreezeAccount freezeAccount, long targetUseId);

    // just for SeataTCC
    @TwoPhaseBusinessAction(name = "DollarAccountService", commitMethod = "commit", rollbackMethod = "cancel")
    FreezeAccount prepare(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "order") Order order);
    boolean commit(BusinessActionContext actionContext);
    boolean cancel(BusinessActionContext actionContext);

    // just for SeataAT
    boolean dollarTransfer(Order order);
}
