package com.example.webmedia.Mapper;

import com.example.webmedia.model.VcRole;
import com.example.webmedia.model.VcUserRole;
import com.example.webmedia.model.VcUserRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface VcUserRoleMapper {
    @SelectProvider(type=VcUserRoleSqlProvider.class, method="countByExample")
    int countByExample(VcUserRoleExample example);

    @DeleteProvider(type=VcUserRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(VcUserRoleExample example);

    @Insert({
        "insert into vc_user_role (uid, rid)",
        "values (#{uid,jdbcType=BIGINT}, #{rid,jdbcType=BIGINT})"
    })
    int insert(VcUserRole record);

    @InsertProvider(type=VcUserRoleSqlProvider.class, method="insertSelective")
    int insertSelective(VcUserRole record);

    @SelectProvider(type=VcUserRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="rid", property="rid", jdbcType=JdbcType.BIGINT)
    })
    List<VcUserRole> selectByExample(VcUserRoleExample example);

    @UpdateProvider(type=VcUserRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VcUserRole record, @Param("example") VcUserRoleExample example);

    @UpdateProvider(type=VcUserRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VcUserRole record, @Param("example") VcUserRoleExample example);


    @Select("select * from vc_user_role where uid = #{id}")
    @Results({
            @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
            @Result(column="rid", property="rid", jdbcType=JdbcType.BIGINT),
            @Result(property = "role", column = "rid",
                    one = @One(select = "com.example.webmedia.Mapper.VcRoleMapper.seletRolesByRid"))
    })
    List<VcUserRole> selectRolesByUid(@Param("id") Long uid);
}