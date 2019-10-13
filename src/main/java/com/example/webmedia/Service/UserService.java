package com.example.webmedia.Service;

import com.example.webmedia.model.VcUser;

/**
* @program: UserService.java
*
* @author: xty
*
* @create: 2019/10/13/013
**/

public interface UserService {


    /**
     * 注册
     * @param user
     * @return
     */
    public Integer registerUser(VcUser user);


    /**
     * 通过usernmae寻找用户
     * @param username
     * @return
     */
    public VcUser findUserByUsername(String username);

}
