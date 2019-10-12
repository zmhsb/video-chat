package com.example.webmedia.Service;

import com.example.webmedia.Mapper.RegisterMapper;
import com.example.webmedia.model.Consumer;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterMapper registerMapper;

    @Override
    public Integer regist(Consumer consumer) {

        String hashAlgorithm = "MD5";
        Object credentials = consumer.getPassword();
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithm,credentials,consumer.getUsername(),hashIterations);
        consumer.setPassword(result.toString());
        consumer.setIdentity("user");
        consumer.setPoints(0);
        consumer.setGrade("none");
        Integer ID = registerMapper.register(consumer);
        return ID;
    }
}
