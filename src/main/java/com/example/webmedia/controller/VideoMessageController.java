package com.example.webmedia.controller;


import com.example.webmedia.Template.MessageTemplate;
import com.example.webmedia.model.InMessage;
import com.example.webmedia.model.VideoMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.msgpack.annotation.Message;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@MessageMapping("/video")
/**
* @program: VideoController.java
*
* @author: xty
*
* @create: 2019/10/13/013
**/

public class VideoMessageController {

    @Autowired
    MessageTemplate messageTemplate;
    public static Map<String,String> nowMovie = new HashMap<>();

    @MessageMapping("/start")
    public void videoStart(InMessage inMessage)
    {
        String t = inMessage.getContent();
        Double tt = Double.valueOf(t);

        Long time = new Double(Math.ceil(tt)).longValue();

        System.out.println(time);
        nowMovie.put("currentMovieName",inMessage.getFrom());
        nowMovie.put("currentMovieStartTime",String.valueOf(time*1000));
        nowMovie.put("MovieStartSystemTime", String.valueOf(System.currentTimeMillis()));
        nowMovie.put("MovieStatus","start");
        messageTemplate.Send_Movie_Status();

    }

    @MessageMapping("/pause")
    public void videoPause(InMessage inMessage)
    {
        String t = inMessage.getContent();
        Double tt = Double.valueOf(t);

        Long time = new Double(Math.ceil(tt)).longValue();
        nowMovie.put("MovieStatus","pause");
        nowMovie.put("currentMovieStartTime",String.valueOf(time*1000));
        messageTemplate.Send_Movie_Status();
    }


    @MessageMapping("/changeMovie")
    public void videoChange(InMessage inMessage)
    {
        nowMovie.put("currentMovieName",inMessage.getFrom());
        nowMovie.put("currentMovieStartTime","0");
        nowMovie.put("MovieStartSystemTime", String.valueOf(System.currentTimeMillis()));
        nowMovie.put("MovieStatus","change");
        messageTemplate.Send_Movie_Status();
    }
}
