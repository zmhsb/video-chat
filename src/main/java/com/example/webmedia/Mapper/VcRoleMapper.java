package com.example.webmedia.Mapper;

import com.example.webmedia.model.VcRole;
import com.example.webmedia.model.VcRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import javax.management.relation.Role;

public interface VcRoleMapper {
    @SelectProvider(type=VcRoleSqlProvider.class, method="countByExample")
    int countByExample(VcRoleExample example);

    @DeleteProvider(type=VcRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(VcRoleExample example);

    @Insert({
        "insert into vc_role (name)",
        "values (#{name,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(VcRole record);

    @InsertProvider(type=VcRoleSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(VcRole record);

    @SelectProvider(type=VcRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<VcRole> selectByExample(VcRoleExample example);

    @UpdateProvider(type=VcRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VcRole record, @Param("example") VcRoleExample example);

    @UpdateProvider(type=VcRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VcRole record, @Param("example") VcRoleExample example);


    @Select("select * from vc_role where id = #{rid}")
    VcRole seletRolesByRid(@Param("rid") Long rid);
}