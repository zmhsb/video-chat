package com.example.webmedia.Mapper;

import com.example.webmedia.model.VcUser;
import com.example.webmedia.model.VcUserExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface VcUserMapper {
    @SelectProvider(type=VcUserSqlProvider.class, method="countByExample")
    int countByExample(VcUserExample example);

    @DeleteProvider(type=VcUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(VcUserExample example);

    @Insert({
        "insert into vc_user (username, password, ",
        "sex, birthday, point)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=TINYINT}, #{birthday,jdbcType=DATE}, #{point,jdbcType=BIGINT})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(VcUser record);

    @InsertProvider(type=VcUserSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(VcUser record);

    @SelectProvider(type=VcUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="point", property="point", jdbcType=JdbcType.BIGINT),
        @Result(property = "rolesId", column = "id",
                many = @Many(select = "com.example.webmedia.Mapper.VcUserRoleMapper.selectRolesByUid"))
    })
    List<VcUser> selectByExample(VcUserExample example);

    @UpdateProvider(type=VcUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VcUser record, @Param("example") VcUserExample example);

    @UpdateProvider(type=VcUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VcUser record, @Param("example") VcUserExample example);
}