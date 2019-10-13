package com.example.webmedia.controller;

import com.alibaba.fastjson.JSONObject;

import com.example.webmedia.Service.UserService;
import com.example.webmedia.model.VcUser;
import com.example.webmedia.model.BackMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/user")
/**
* @program: LoginController.java
*
* @author: xty
*
* @create: 2019/10/13/013
**/

public class LoginController {


    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public BackMessage login(VcUser user, HttpServletRequest request)
    {
        System.out.println(user);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            BackMessage backMessage = BackMessage.buildSuccess();
            backMessage.setUsername(user.getUsername());
            //添加身份
            Object principal = subject.getPrincipals();
            VcUser primaryPrincipal = (VcUser) ((PrincipalCollection) principal).getPrimaryPrincipal();
            System.out.println(primaryPrincipal);
            return backMessage;
        }catch (AuthenticationException e)
        {
            e.printStackTrace();
            return BackMessage.buildFail();
        }
    }


    @PostMapping("/register")
    public BackMessage Register(VcUser user)
    {
        System.out.println(user);
        Integer integer = userService.registerUser(user);
        if(integer!=0)
        {
            System.out.println("注册成功");
            BackMessage back = new BackMessage(200,null,null);
            return back;
        }
        System.out.println("注册失败");
        BackMessage back = new BackMessage(404,null,null);
        return back;
    }


    @RequestMapping("/get_session")
    public BackMessage logout(HttpServletRequest request)
    {
         HttpSession session = request.getSession();
         BackMessage back = new BackMessage();
         back.setSign(404);
         if(session.getAttribute("name")!=null)
         {
             back.setSign(200);
             String name = (String) session.getAttribute("name");
             String role = (String) session.getAttribute("role");
             JSONObject jsonObject = new JSONObject();

             jsonObject.put("name",name);
             jsonObject.put("role",role);

             back.setContent(jsonObject);
             return back;
         }
         return back;
    }

}
