package com.example.advisewebsocket;


import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GPSInfoDTO {

    private String message;
    private String timeStamp;

}
