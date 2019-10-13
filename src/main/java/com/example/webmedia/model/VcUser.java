package com.example.webmedia.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class VcUser  {
    private Long id;

    private String username;

    private String password;

    private String sex;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private Long point;

    List<VcUserRole> rolesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "VcUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", point=" + point +
                ", rolesId=" + rolesId +
                '}';
    }

    public List<VcUserRole> getRolesId() {

        return rolesId;
    }

    public void setRolesId(List<VcUserRole> rolesId) {
        this.rolesId = rolesId;
    }

}