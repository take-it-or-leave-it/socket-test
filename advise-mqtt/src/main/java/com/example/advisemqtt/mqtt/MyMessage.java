package com.example.advisemqtt.mqtt;

import lombok.*;

import java.text.SimpleDateFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MyMessage {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss.SSS");

    private String title;
    private String message;
    private String timeStamp;
}
