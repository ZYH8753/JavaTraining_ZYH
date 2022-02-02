package mymq.codec;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import mymq.core.MymqMessage;

import java.util.List;

public class HttpProtocolDecoder extends MessageToMessageDecoder<FullHttpRequest> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest, List<Object> list) throws Exception {
        String str = fullHttpRequest.content().toString(CharsetUtil.UTF_8);
        MymqMessage message = JSONObject.parseObject(str, MymqMessage.class);
        list.add(message);
    }
}
