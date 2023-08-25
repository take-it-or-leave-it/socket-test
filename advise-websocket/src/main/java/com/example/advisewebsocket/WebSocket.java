package com.example.advisewebsocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class WebSocket extends TextWebSocketHandler {
    private  static Set<Session> clients =
            Collections.synchronizedSet(new HashSet<Session>());
    private static final ObjectMapper mapper =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        GPSInfoDTO gps_object = mapper.readValue(message.getPayload(), GPSInfoDTO.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss.SSS");
        log.info("Received message : {}, Time : {}" ,gps_object.getTimeStamp(), dateFormat.format(System.currentTimeMillis()));

    }

//        @OnMessage
//    public void onMessage(String msg, Session session) throws  Exception{
//
//        GPSInfoDTO gps_object = mapper.readValue(msg, GPSInfoDTO.class);
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss.SSS");
//        log.info("Received message : {}, Time : {}" ,gps_object.toString(), dateFormat.format(System.currentTimeMillis()));
//        /*
//        for(Session s : clients){
//
//            s.getBasicRemote().sendText(msg);
//            }
//         */
//    }
//
//    @OnOpen
//    public void onOpen(Session session){
//
//        if(!clients.contains(session)) {
//            clients.add(session);
//            System.out.println("Open new session : " + session.toString());
//        }else{
//            System.out.println("Already connected : "+ session.toString());
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session){
//        System.out.println("Close connection : "+ session.toString());
//        clients.remove(session);
//    }

}