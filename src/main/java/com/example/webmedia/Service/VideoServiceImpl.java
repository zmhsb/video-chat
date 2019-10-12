package com.example.webmedia.Service;

import com.example.webmedia.Mapper.VideoMapper;
import com.example.webmedia.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;
    @Override
    public Integer Video_id() {

        Video video = new Video();
        videoMapper.Get_Video_ID(video);

//        System.out.println(video.getId()-+);
        return video.getId();
    }

    @Override
    public Integer Video_complete(Video video) {
        return videoMapper.Complete_Video(video);
    }

    @Override
    public Video Get_Video(Integer id) {
        Video video = videoMapper.Get_Video(id);
        return video;
    }

    @Override
    public List<Video> selectAll() {
        List<Video> list_video = videoMapper.get_list_video();
        return list_video;
    }
}
