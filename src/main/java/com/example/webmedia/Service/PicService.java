package com.example.webmedia.Service;

import com.example.webmedia.model.Pic;

import java.util.List;

/**
* @program: PicService.java
*
* @author: xty
*
* @create: 2019/10/17/017
**/

public interface PicService {

    /**
     * 保存图片
     * @param pic
     * @return
     */
    public Integer savePic(Pic pic);


    /**
     * 获取图片SRC
     * @param picId
     * @return
     */
    public String getPicSrc(Integer picId);


    /**
     * 获得礼物列表
     * @return
     */
    public List<Pic> getPicList();
}
