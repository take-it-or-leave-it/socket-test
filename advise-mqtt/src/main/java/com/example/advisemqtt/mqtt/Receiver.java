package com.example.advisemqtt.mqtt;


import com.rabbitmq.client.RpcClient;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class Receiver {
//    private static final Logger log

    @RabbitListener(queues = "hello.queue")
    public void consume(MyMessage message){
        String current = MyMessage.dateFormat.format(System.currentTimeMillis());
        log.info("Received Message : {}, Current Time : {}", message.toString(), current);
    }
}
