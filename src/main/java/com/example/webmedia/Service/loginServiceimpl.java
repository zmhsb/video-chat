package com.example.webmedia.Service;


import com.example.webmedia.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.example.webmedia.Mapper.LoginMapper;
@Service
public class loginServiceimpl implements loginService {

    @Autowired
    LoginMapper loginMapper;
    @Override
    public Consumer QuerybyName(String name) {
        return loginMapper.QueryUserByName(name);
    }
}
