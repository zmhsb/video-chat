package com.example.webmedia.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webmedia.Service.RegisterService;
import com.example.webmedia.model.Consumer;

import com.example.webmedia.model.backMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
public class LoginController {


    @Autowired
    RegisterService registerService;

    @RequestMapping("/login")
    @ResponseBody
    public backMessage login(Consumer consumer,HttpServletRequest request)
    {
        System.out.println(consumer);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(consumer.getUsername(),consumer.getPassword());
        try {
            subject.login(token);
            if(subject.hasRole("admin"))
            {
              //  System.out.println();
                HttpSession session = request.getSession();
                session.setAttribute("role","admin");
                session.setAttribute("username",consumer.getUsername());
                backMessage back = new backMessage(200,"admin",consumer.getUsername());
                return back;
               // return "redirect:/admin";
            }else
            {
                System.out.println("user");
                HttpSession session = request.getSession();
                session.setAttribute("role","user");
                session.setAttribute("username",consumer.getUsername());
                backMessage back = new backMessage(200,"user",consumer.getUsername());
              // return "redirect:/user";
                return back;
            }
        }catch (AuthenticationException e)
        {
            e.printStackTrace();
            backMessage back = new backMessage(404,null,consumer.getUsername());
            return back;
          //  return "redirect:/";
        }
    }


    @RequestMapping("/register")
    @ResponseBody
    public backMessage Register(Consumer consumer)
    {
        System.out.println(consumer);
        Integer regist = registerService.regist(consumer);
        if(regist!=0)
        {
            System.out.println("注册成功");
            backMessage back = new backMessage(200,null,null);
            return back;
          //  return "login";
        }
        System.out.println("注册失败");
        backMessage back = new backMessage(404,null,null);
        return back;
       // return "register";
    }

    @RequestMapping("/get_session")
    @ResponseBody
    public backMessage logout(HttpServletRequest request)
    {
         HttpSession session = request.getSession();
         backMessage back = new backMessage();
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
