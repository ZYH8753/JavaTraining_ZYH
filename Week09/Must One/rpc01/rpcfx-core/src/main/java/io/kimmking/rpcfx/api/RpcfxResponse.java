package io.kimmking.rpcfx.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RpcfxResponse {
    private Object result;
    private boolean status;
    private Exception exception;
}
