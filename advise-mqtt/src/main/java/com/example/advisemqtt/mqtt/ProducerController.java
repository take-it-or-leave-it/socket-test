package com.example.advisemqtt.mqtt;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final Producer producer;

    @PostMapping("/send")
    public String post(@RequestBody MyMessage message){
        producer.sendMessage(message);
        return "Okay";
    }

    @GetMapping("/send")
    public String get(){
        return "Good";
    }
}
