package org.ssafy.pasila.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/id");  //sub
        registry.setApplicationDestinationPrefixes("/send");  //pub

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){

        registry.addEndpoint("/stomp/pasila")   //클라이언트가 Handshake를 위해 연결해야하는 end-point의 url
//                .setAllowedOrigins("http://127.0.0.1")
                .setAllowedOrigins("*")
                .withSockJS();

    }

}
