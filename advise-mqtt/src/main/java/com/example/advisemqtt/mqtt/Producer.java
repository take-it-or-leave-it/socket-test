package com.example.advisemqtt.mqtt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.logging.Logger;

import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {
//    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(MyMessage message){
        log.info("About to send : {}", message.toString());
        message.setTimeStamp(MyMessage.dateFormat.format(System.currentTimeMillis()));
        //System.out.println("Before Send : "+message.toString());
        rabbitTemplate.convertAndSend("hello.exchange", "hello.key", message);
    }
}
