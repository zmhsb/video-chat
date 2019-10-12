package com.example.webmedia.Service;

import com.example.webmedia.model.Video;

import java.util.List;

public interface VideoService {

    public Integer Video_id();


    public Integer Video_complete(Video video);


    public Video Get_Video(Integer id);


    public List<Video> selectAll();
}
