package com.example.webmedia.controller;

import com.example.webmedia.Template.MessageTemplate;
import com.example.webmedia.model.InMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    MessageTemplate messageTemplate;

    public static Map<String,String> onlineUser = new HashMap<>();

    @MessageMapping("/single/add")
    public void add_user(InMessage inMessage, SimpMessageHeaderAccessor headerAccessor)
    {
        String sessionid = headerAccessor.getSessionId();
        System.out.println("登录的sessionid====  "+sessionid);
        onlineUser.put(sessionid,inMessage.getFrom());
  //      System.out.println("   "+inMessage.getFrom()+"    "+onlineUser.size());

    }


    @MessageMapping("/single/chat")
    public void chat(InMessage message){
        messageTemplate.SendMes(message);
    }



    @Scheduled(fixedRate = 5000)
    public void onlineUser() {
        messageTemplate.Send_User_Number(onlineUser);
    }


}
