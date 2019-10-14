package com.example.webmedia.Template;

import com.example.webmedia.controller.MessageController;
import com.example.webmedia.model.InMessage;
import com.example.webmedia.model.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageTemplate {

    @Autowired
    SimpMessagingTemplate template;


    public void SendMes(InMessage message)
    {
        template.convertAndSend("/topic/chat/together",new OutMessage(message.getFrom()+":     "+message.getContent()));
    }


    public void Send_User_Number()
    {
        template.convertAndSend("/topic/onlineuser",new OutMessage("当前在线人数:  "+MessageController.onlineUser.size()+"人"));
    }

}
