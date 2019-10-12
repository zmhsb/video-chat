package com.example.webmedia.Mapper;

import com.example.webmedia.model.Video;
import com.example.webmedia.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VideoMapper {

    /**
     * 给影片注册一个id
     * @return
     */
    @Insert("insert into video(name,src) values(#{name},#{src})")
    @Options(useGeneratedKeys = true, keyProperty="id", keyColumn="id")//加入该注解可以保持对象后，查看对象插入id
    public int Get_Video_ID(Video video);


    /**
     * 添加影片
     * @param video
     */
    @UpdateProvider(type = VideoProvider.class,method = "updateVideo")
    public Integer Complete_Video(Video video);


    @Select("select * from video where id = #{id}")
    public Video Get_Video(@Param("id") Integer id);


    @SelectProvider(type = VideoProvider.class,method = "select_except_src_All")
    public List<Video> get_list_video();
}
