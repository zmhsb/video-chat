package com.example.webmedia.Service.Impl;

import com.example.webmedia.Mapper.VcUserMapper;
import com.example.webmedia.Service.UserService;
import com.example.webmedia.model.VcUser;
import com.example.webmedia.model.VcUserExample;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    VcUserMapper userMapper;

    @Override
    public Integer registerUser(VcUser user) {
        String hashAlgorithm = "MD5";
        Object credentials = user.getPassword();
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithm,credentials,user.getUsername(),hashIterations);
        user.setPassword(result.toString());
        int i = userMapper.insertSelective(user);
        return i;
    }

    @Override
    public VcUser findUserByUsername(String username) {
        VcUserExample example = new VcUserExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<VcUser> vcUsers = userMapper.selectByExample(example);
        if(vcUsers!=null)
        {
            return vcUsers.get(0);
        }
        return null;
    }
}
