package com.example.webmedia.Service.Impl;

import com.example.webmedia.Mapper.VcVideoMapper;
import com.example.webmedia.Service.VideoService;
import com.example.webmedia.model.VcVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VcVideoMapper videoMapper;

    @Override
    public List<VcVideo> videoList() {
        List<VcVideo> vcVideos = videoMapper.selectByExample(null);
        return vcVideos;
    }

    @Override
    public Integer saveVideo(VcVideo vcVideo) {
        int i = videoMapper.insertSelective(vcVideo);
        return i;
    }

    @Override
    public String getVideoSrcByVideoName(String videoName) {
        String s = videoMapper.gerVideoSrc(videoName);
        String[] split = s.split("/");
        System.out.println(split[split.length-1]);
        return split[split.length-1];
    }
}
