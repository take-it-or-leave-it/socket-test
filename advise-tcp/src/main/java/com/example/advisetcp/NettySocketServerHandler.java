package com.example.advisetcp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

@ChannelHandler.Sharable
@Slf4j
public class NettySocketServerHandler extends ChannelInboundHandlerAdapter {

    private static final ObjectMapper mapper =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JsonProcessingException {
        String readMessage = ((ByteBuf) msg).toString(Charset.forName("UTF8"));

        MyMessage message_object = mapper.readValue(readMessage, MyMessage.class);

        ctx.write(msg);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss.SSS");
        log.info("Received : {}, Current : {}",message_object.toString(), dateFormat.format(System.currentTimeMillis()));
        //System.out.println("message from received: " + readMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
