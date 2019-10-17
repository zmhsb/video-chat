package com.example.webmedia.Template;

import com.example.webmedia.Mapper.VcVideoMapper;
import com.example.webmedia.Service.VideoService;
import com.example.webmedia.controller.MessageController;
import com.example.webmedia.controller.VideoMessageController;
import com.example.webmedia.model.InMessage;
import com.example.webmedia.model.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageTemplate {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    VideoService videoService;

    public void SendMes(InMessage message)
    {
        template.convertAndSend("/topic/chat/together",new OutMessage(message.getFrom()+":     "+message.getContent()));
    }


    public void Send_User_Number()
    {
        template.convertAndSend("/topic/onlineuser",new OutMessage("当前在线人数:  "+MessageController.onlineUser.size()+"人"));
    }

    public void Send_Movie_Status(){
        OutMessage outMessage = new OutMessage();
        String movieName = "";
        String pathName = "";
        if(VideoMessageController.nowMovie.get("currentMovieName")==null)
        {
            outMessage.setStatus("nothing");
        }else
        {
            switch (VideoMessageController.nowMovie.get("MovieStatus"))
            {
                case "start":
                    movieName = VideoMessageController.nowMovie.get("currentMovieName");
                    //计算电影播放了多久
                    Long movieStartSystemTime = Long.valueOf(VideoMessageController.nowMovie.get("MovieStartSystemTime"));
                    Long currentMovieStartTime = Long.valueOf(VideoMessageController.nowMovie.get("currentMovieStartTime"));
                    Long durationTime = System.currentTimeMillis() - movieStartSystemTime;
                    pathName = videoService.getVideoSrcByVideoName(movieName);
                    outMessage.setPath(pathName);
                    outMessage.setFrom(movieName);
                    outMessage.setContent(String.valueOf((durationTime+currentMovieStartTime)/1000));
                    outMessage.setStatus("start");
                    break;
                case "pause":
                    outMessage.setFrom(VideoMessageController.nowMovie.get("currentMovieName"));
                    outMessage.setStatus("pause");
                    break;
                case "change":
                    movieName = VideoMessageController.nowMovie.get("currentMovieName");
                    outMessage.setFrom(movieName);
                    pathName = videoService.getVideoSrcByVideoName(movieName);
                    outMessage.setPath(pathName);
                    outMessage.setStatus("change");
                    VideoMessageController.nowMovie.put("MovieStatus","start");
                    break;
            }
        }
        template.convertAndSend("/topic/movieStatus",outMessage);
    }

}
