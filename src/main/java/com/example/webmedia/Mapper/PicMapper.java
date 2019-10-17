package com.example.webmedia.Mapper;

import com.example.webmedia.model.Pic;
import com.example.webmedia.model.PicExample;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PicMapper {
    @SelectProvider(type=PicSqlProvider.class, method="countByExample")
    int countByExample(PicExample example);

    @DeleteProvider(type=PicSqlProvider.class, method="deleteByExample")
    int deleteByExample(PicExample example);

    @Insert({
        "insert into pic (src, picExt, ",
        "type)",
        "values (#{src,jdbcType=VARCHAR}, #{picext,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Pic record);

    @InsertProvider(type=PicSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(Pic record);

    @SelectProvider(type=PicSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="src", property="src", jdbcType=JdbcType.VARCHAR),
        @Result(column="picExt", property="picext", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<Pic> selectByExample(PicExample example);

    @UpdateProvider(type=PicSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Pic record, @Param("example") PicExample example);

    @UpdateProvider(type=PicSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Pic record, @Param("example") PicExample example);
}