package com.example.webmedia;

import com.example.webmedia.Mapper.VcUserMapper;
import com.example.webmedia.Mapper.VcVideoMapper;
import com.example.webmedia.model.VcUser;
import com.example.webmedia.model.VcUserExample;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(value = "com.example.webmedia.Mapper")
public class WebMediaApplicationTests {

    @Autowired
    VcUserMapper userMapper;

    @Autowired
    VcVideoMapper vcVideoMapper;
//    group1/M00/00/00/CgtIFl2l306ATYphAVNFNiVg-Ik166.mp4
//    文件上传
    @Test
    public void contextLoads(){
        try {
            ClientGlobal.initByProperties("application.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //tracker客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //storage客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", "music.mp4");

            String fileId = client.upload_file1("D:\\web-mediaa\\src\\main\\resources\\static\\video\\music.mp4", "mp4", metaList);
            System.out.println("upload success. file id is: " + fileId);

            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


//    文件查询
    @Test
      public void queryFiles()
      {
          try {
              ClientGlobal.initByProperties("application.properties");
              System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
              System.out.println("charset=" + ClientGlobal.g_charset);

              //tracker客户端
              TrackerClient tracker = new TrackerClient();
              TrackerServer trackerServer = tracker.getConnection();
              StorageServer storageServer = null;
              //storage客户端
              StorageClient1 client = new StorageClient1(trackerServer, storageServer);

              //查询文件2种方式
             // FileInfo group1 = client.query_file_info("group1", "M00/00/00/CgtIFl2l306ATYphAVNFNiVg-Ik166.mp4");
              FileInfo fileInfo = client.query_file_info1("group1/M00/00/00/CgtIFl2l306ATYphAVNFNiVg-Ik166.mp4");

              //查询文件元信息2种方式
              NameValuePair[] metadata1 = client.get_metadata1("group1/M00/00/00/CgtIFl2l306ATYphAVNFNiVg-Ik166.mp4");
              NameValuePair[] metadata12 = client.get_metadata("group1","M00/00/00/CgtIFl2l306ATYphAVNFNiVg-Ik166.mp4");


              System.out.println(metadata1);

              System.out.println(fileInfo);

              trackerServer.close();
          } catch (Exception ex) {
              ex.printStackTrace();
          }
      }


//      下载文件
    @Test
    public void downLoadFile() throws IOException, MyException {
        ClientGlobal.initByProperties("application.properties");
        System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        System.out.println("charset=" + ClientGlobal.g_charset);

        //tracker客户端
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        //storage客户端
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        int i = client.delete_file1("group1/M00/00/00/CgtIFl2m9eWAB_gKABZ0sEPPR9Q995.mp4");
        System.out.println(i);


        trackerServer.close();
    }

    @Test
    public void tesrt()
    {
        String s = vcVideoMapper.gerVideoSrc("music.mp4");
        System.out.println(s);
    }
}

