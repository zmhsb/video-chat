package com.example.webmedia.Mapper;

import com.example.webmedia.model.Consumer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RegisterMapper {

    @Insert("INSERT INTO consumer(username,password,points,grade,identity) VALUES(#{username},#{password},#{points},#{grade},#{identity})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")//加入该注解可以保持对象后，查看对象插入id
    public Integer register(Consumer consumer);
}
