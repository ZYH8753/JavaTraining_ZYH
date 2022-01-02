package io.kimmking.rpcfx.demo.consumer.netty;


import io.kimmking.rpcfx.api.RpcfxResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestPendingCenter {

    private Map<Integer, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add(Integer streamId, OperationResultFuture future){
        this.map.put(streamId, future);
    }

    public void set(Integer streamId, RpcfxResponse rpcfxResponse){
        OperationResultFuture operationResultFuture = map.get(streamId);
        if (operationResultFuture != null) {
            operationResultFuture.setSuccess(rpcfxResponse);
            map.remove(streamId);
        }
     }


}
