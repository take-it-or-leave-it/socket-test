package com.example.advisewebsocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;



import java.util.Map;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer { // (1)


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocket(), "/gps"); // (2)
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean(); // (3)
        container.setMaxTextMessageBufferSize(1024 * 1024 * 10); // (4)
        container.setMaxBinaryMessageBufferSize(1024 * 1024 * 10); // (5)
        return container;
    }
}
