package com.example.webmedia.Mapper;

import com.example.webmedia.model.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface LoginMapper {

    @Select("select * from consumer where username = #{username}")
    public Consumer QueryUserByName(String username);

}
