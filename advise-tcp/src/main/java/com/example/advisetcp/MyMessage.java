package com.example.advisetcp;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MyMessage {
    private String message;
    private String timeStamp;
    private String title;
}
