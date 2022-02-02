package mymq.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import mymq.core.MymqResponse;

import java.util.List;

public class HttpProtocolEncoder extends MessageToMessageEncoder<MymqResponse> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MymqResponse mymqResponse, List<Object> list) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer().writeBytes(JSON.toJSONString(mymqResponse).getBytes());
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, buffer);
        fullHttpResponse.headers().set("Content-Type", "text/plain;charset=UTF-8");
        fullHttpResponse.headers().set("Content-Length", fullHttpResponse.content().readableBytes());
        list.add(fullHttpResponse);
    }
}
