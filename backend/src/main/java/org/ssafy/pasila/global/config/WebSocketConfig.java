package org.ssafy.pasila.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
//@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/id");  //sub
        registry.setApplicationDestinationPrefixes("/send");  //pub

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){

        registry.addEndpoint("pasila");
        registry.addEndpoint("/pasila")   //클라이언트가 Handshake를 위해 연결해야하는 end-point의 url
                .setAllowedOrigins("*")
                .withSockJS();

    }

}
