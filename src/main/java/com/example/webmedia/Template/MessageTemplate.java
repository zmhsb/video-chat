package com.example.webmedia.Template;

import com.example.webmedia.model.InMessage;
import com.example.webmedia.model.OutMessage;
import com.example.webmedia.model.User;
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
        template.convertAndSend("/chat/together",new OutMessage(message.getFrom()+": "+message.getContent()));
    }


    public void Send_User_Number(Map<String,String> people)
    {
        String user = "";
        for (String u:people.values()
             ) {
          //  System.out.println("sdasdas   "+u);
            user+=u;
            user+="--";
        }
        System.out.println(user);
        template.convertAndSend("/topic/onlineuser",new OutMessage("当前在线人数:"+people.size()+": "+user));
    }

}
