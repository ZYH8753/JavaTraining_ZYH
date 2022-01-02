package io.kimmking.rpcfx.demo.consumer.netty;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class JsonEecoder extends MessageToMessageEncoder<RpcfxRequest> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcfxRequest rpcfxRequest, List<Object> list) throws Exception {
        list.add(JSON.toJSONString(rpcfxRequest));
    }
}
