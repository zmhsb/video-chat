package com.example.webmedia.controller;


import com.example.webmedia.Service.PicService;
import com.example.webmedia.Service.VideoService;
import com.example.webmedia.model.BackMessage;
import com.example.webmedia.model.Pic;
import com.example.webmedia.model.VcVideo;
import com.example.webmedia.utils.FastDfsUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    PicService picService;

    @Autowired
    FastDfsUtils utils;

    @GetMapping("/getVideoList")
    public BackMessage getVideoList()
    {
        List<VcVideo> vcVideos = videoService.videoList();
        BackMessage backMessage = BackMessage.buildSuccess();
        backMessage.setContent(vcVideos);
        return backMessage;
    }

    @GetMapping("/getPicList")
    @ResponseBody
    public BackMessage getPicList()
    {
        List<Pic> picList = picService.getPicList();
        List<String> collect = picList.stream().map(pic -> {
            String path = pic.getSrc();
            String[] split = path.split("/");
            return split[split.length - 1];
        }).collect(Collectors.toList());

        BackMessage backMessage = BackMessage.buildSuccess();
        backMessage.setContent(collect);
        return backMessage;
    }


    @PostMapping("/uploadMovie")
    @ResponseBody
    public BackMessage upload(@RequestParam("movie") MultipartFile file) throws IOException {
        String filename = file.getResource().getFilename();
        System.out.println(filename);
        String[] split = filename.split("\\.");

        try {
            switch (split[1].toLowerCase())
            {
                case "jpg":
                    Pic pic = utils.uploadPic(file.getInputStream(), filename, split[1]);
                    picService.savePic(pic);
                    break;
                case "mp4":
                    VcVideo vcVideo = utils.uploadVideo(file.getInputStream(), filename, split[1]);
                    videoService.saveVideo(vcVideo);
                    break;
            }
            return  BackMessage.buildSuccess();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return BackMessage.buildFail();
    }

    @GetMapping("/getVideo")
    public void getVideo(@RequestParam("fileId") String fileId,HttpServletResponse response) throws IOException, MyException {
        ClientGlobal.initByProperties("application.properties");
        System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        System.out.println("charset=" + ClientGlobal.g_charset);

        //tracker客户端
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        //storage客户端
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        byte[] bytes = new byte[1024];
        //下载文件
        byte[] movie = client.download_file1(fileId);

        ServletOutputStream outputStream = response.getOutputStream();


        InputStream inputStream1 = new ByteArrayInputStream(movie);

        int num = 0;
        while((num=inputStream1.read(bytes))!=-1)
        {
            outputStream.write(bytes,0,num);
        }

        outputStream.flush();
        outputStream.close();
        trackerServer.close();
    }

}
