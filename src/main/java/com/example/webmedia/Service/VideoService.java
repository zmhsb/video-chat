package com.example.webmedia.Service;


import com.example.webmedia.model.VcVideo;

import java.util.List;

/**
* @program: VideoService.java
*
* @author: xty
*
* @create: 2019/10/17/017
**/

public interface VideoService {

    /**
     * 获得所有视频
     * @return
     */
    public List<VcVideo> videoList();


    /**
     * 保存视频
     * @param vcVideo
     * @return
     */
    public Integer saveVideo(VcVideo vcVideo);


    /**
     * 通过videoName 获取 video
     * @param videoName
     * @return
     */
    public String getVideoSrcByVideoName(String videoName);
}
