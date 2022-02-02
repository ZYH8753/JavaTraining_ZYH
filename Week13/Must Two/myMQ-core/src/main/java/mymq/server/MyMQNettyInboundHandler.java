package mymq.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import mymq.core.MymqBroker;
import mymq.core.MymqMessage;
import mymq.core.MymqResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMQNettyInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(MyMQNettyInboundHandler.class);

    private MymqBroker mymqBroker;

    public MyMQNettyInboundHandler(MymqBroker mymqBroker) {
        this.mymqBroker = mymqBroker;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        System.out.println("client is received, address=" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            MymqMessage message = (MymqMessage) msg;
            LOGGER.info("received request is : {}", msg);
            MymqResponse res = null;
            if (message.getMode() == MymqMessage.PRODUCE_MODE) {
                res = mymqBroker.send(message);
            } else if (message.getMode() == MymqMessage.CONSUME_MODE) {
                res = mymqBroker.consume(message);
            } else {
                res = new MymqResponse(false, "mode "+message.getMode()+" is not existed!", null);
            }
            ctx.writeAndFlush(res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
