package com.example.webmedia.provider;

import com.example.webmedia.model.Video;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

/**
 * video构建动态sql语句
 */
public class VideoProvider {


    /**
     * 更新video动态语句
     * @param video
     * @return
     */
    public String updateVideo(final Video video){
        return new SQL(){{

            UPDATE("video");

            //条件写法.
            if(video.getName()!=null){
                SET("name=#{name}");
            }
            if(video.getSrc()!=null){
                SET("src=#{src}");
            }

            WHERE("id=#{id}");


        }}.toString();
    }

    public String selectAll(){
        return new SQL(){{
            SELECT("*");
            FROM("video");
        }}.toString();
    }

    public String select_except_src_All(){
        return new SQL(){{
            SELECT("id","name");
            FROM("video");
        }}.toString();
    }

}
