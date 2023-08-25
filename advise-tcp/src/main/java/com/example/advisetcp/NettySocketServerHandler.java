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

import java.lang.instrument.Instrumentation;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

@ChannelHandler.Sharable
@Slf4j
public class NettySocketServerHandler extends ChannelInboundHandlerAdapter {


    private ByteBuf buffer;
//    private String currentResult = "";
    private static final ObjectMapper mapper =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws JsonProcessingException {
        //log.info("raw data : {}",msg.toString());

        ByteBuf chunk = (ByteBuf) msg;
//        log.info("Object size : {}", chunk.readableBytes());

        int chunk_size = chunk.readableBytes();
        if (buffer == null) {
            buffer = ctx.alloc().buffer();
        }

//        while(chunk.isReadable()){
//            char temp = (char) chunk.readByte();
//            currentResult += temp;
//            if (temp == '}') {
//
//                MyMessage message_object = mapper.readValue(currentResult, MyMessage.class);
//                SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss.SSS");
//                log.info("Received : {}, Current : {}",message_object.getTimeStamp(), dateFormat.format(System.currentTimeMillis()));
//                currentResult = "";
//
//            }
//        }
        buffer.writeBytes(chunk);

        if (chunk_size <= 100000){

            byte[] data = new byte[buffer.readableBytes()];
            buffer.readBytes(data);
            String currentResult = new String(data);

            MyMessage message_object = mapper.readValue(currentResult, MyMessage.class);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss.SSS");
            log.info("Received : {}, Current : {}",message_object.getTimeStamp(), dateFormat.format(System.currentTimeMillis()));

//            String readMessage = (buffer).toString(StandardCharsets.UTF_8);
            buffer.release();
            buffer = null;

        }
        chunk.release();

          //System.out.println("message from received: " + readMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
