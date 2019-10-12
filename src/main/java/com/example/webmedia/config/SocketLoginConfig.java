package com.example.webmedia.config;

import com.example.webmedia.controller.MessageController;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SocketLoginConfig implements ApplicationListener<SessionDisconnectEvent> {

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        String sessionId = sessionDisconnectEvent.getSessionId();
        MessageController.onlineUser.remove(sessionId);
        System.out.println(sessionId+"         下线了");
    }
}
