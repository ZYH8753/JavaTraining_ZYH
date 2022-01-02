package io.kimmking.rpcfx.demo.consumer.netty;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.demo.api.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private RequestPendingCenter requestPendingCenter;

    public ResponseDispatcherHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse responseMessage) throws Exception {
        ByteBuf buf = responseMessage.content();
        String result = buf.toString(CharsetUtil.UTF_8);
        RpcfxResponse rpcfxResponse = JSON.parseObject(result, RpcfxResponse.class);
        User user = JSON.parseObject(rpcfxResponse.getResult().toString(), User.class);
        requestPendingCenter.set(user.getId(), rpcfxResponse);
    }
}
