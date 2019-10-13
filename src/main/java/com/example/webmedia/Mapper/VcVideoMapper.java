package com.example.webmedia.Mapper;

import com.example.webmedia.model.VcVideo;
import com.example.webmedia.model.VcVideoExample;
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

public interface VcVideoMapper {
    @SelectProvider(type=VcVideoSqlProvider.class, method="countByExample")
    int countByExample(VcVideoExample example);

    @DeleteProvider(type=VcVideoSqlProvider.class, method="deleteByExample")
    int deleteByExample(VcVideoExample example);

    @Insert({
        "insert into vc_video (name, src, ",
        "intro)",
        "values (#{name,jdbcType=VARCHAR}, #{src,jdbcType=VARCHAR}, ",
        "#{intro,jdbcType=LONGVARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(VcVideo record);

    @InsertProvider(type=VcVideoSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(VcVideo record);

    @SelectProvider(type=VcVideoSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="src", property="src", jdbcType=JdbcType.VARCHAR),
        @Result(column="intro", property="intro", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<VcVideo> selectByExampleWithBLOBs(VcVideoExample example);

    @SelectProvider(type=VcVideoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="src", property="src", jdbcType=JdbcType.VARCHAR)
    })
    List<VcVideo> selectByExample(VcVideoExample example);

    @UpdateProvider(type=VcVideoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VcVideo record, @Param("example") VcVideoExample example);

    @UpdateProvider(type=VcVideoSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") VcVideo record, @Param("example") VcVideoExample example);

    @UpdateProvider(type=VcVideoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VcVideo record, @Param("example") VcVideoExample example);
}