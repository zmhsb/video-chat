package com.example.webmedia;

import com.example.webmedia.Mapper.VcUserMapper;
import com.example.webmedia.model.VcUser;
import com.example.webmedia.model.VcUserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value = "com.example.webmedia.Mapper")
public class WebMediaApplicationTests {

    @Autowired
    VcUserMapper userMapper;

    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {
        VcUserExample example = new VcUserExample();
        example.createCriteria().andUsernameEqualTo("Owcc");

        List<VcUser> vcUsers = userMapper.selectByExample(example);
        System.out.println(vcUsers);
    }

}
