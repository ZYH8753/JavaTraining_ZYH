package io.kimmking.rpcfx.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RpcfxRequest {
  private String serviceClass;
  private String method;
  private Object[] params;
}
