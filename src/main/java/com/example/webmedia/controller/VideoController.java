package com.example.webmedia.controller;


import com.example.webmedia.Service.VideoService;
import com.example.webmedia.model.Video;
import com.example.webmedia.model.VideoMessage;
import com.example.webmedia.model.backMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class VideoController {

    @Autowired
    VideoService videoService;


    @PostMapping("/upload_init")
    public VideoMessage upload()
    {
        Integer id = videoService.Video_id();
        VideoMessage videoMessage = new VideoMessage();
        if(id!=null)
        {
            videoMessage = new VideoMessage(200,id);
            return videoMessage;
        }
        videoMessage = new VideoMessage(404,null);
        return videoMessage;
    }

    @PostMapping(value ="/upload_complete")
    public VideoMessage complete(@RequestBody Video video)
    {
        Integer res = videoService.Video_complete(video);
        if(res!=null)
        {
            VideoMessage videoMessage = new VideoMessage(200,null);
            return videoMessage;
        }
        return new VideoMessage(404,null);
    }

    @PostMapping("/get_video")
    public VideoMessage Get_Video(@RequestParam("id") Integer id)
    {
        Video video = videoService.Get_Video(id);
        if(video!=null)
        {
            VideoMessage videoMessage = new VideoMessage();
            videoMessage.setSign(200);
            videoMessage.setSrc(video.getSrc());
            return videoMessage;
        }
        VideoMessage videoMessage = new VideoMessage(404,null);
        return videoMessage;
    }


    @GetMapping("get_video_all")
    public VideoMessage get_video_all()
    {
        List<Video> videos = videoService.selectAll();
        if(videos!=null)
        {
            VideoMessage videoMessage = new VideoMessage();
            videoMessage.setContent(videos);
            videoMessage.setSign(200);
            return videoMessage;
        }
        return new VideoMessage(404,null);
    }
}
