package com.example.webmedia.Service.Impl;

import com.example.webmedia.Mapper.PicMapper;
import com.example.webmedia.Service.PicService;
import com.example.webmedia.model.Pic;
import com.example.webmedia.model.PicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicServiceImpl implements PicService {

    @Autowired
    private PicMapper picMapper;

    @Override
    public Integer savePic(Pic pic) {
        int i = picMapper.insertSelective(pic);
        return i;
    }

    @Override
    public String getPicSrc(Integer picId) {
        PicExample example = new PicExample();
        example.createCriteria().andIdEqualTo(picId);
        List<Pic> pics = picMapper.selectByExample(example);
        if(pics!=null)
        {
            return pics.get(0).getSrc();
        }
        return null;
    }

    @Override
    public List<Pic> getPicList() {
        List<Pic> pics = picMapper.selectByExample(null);
        return pics;
    }
}
