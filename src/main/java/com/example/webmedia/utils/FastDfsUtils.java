package com.example.webmedia.utils;

import com.example.webmedia.model.Pic;
import com.example.webmedia.model.VcVideo;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FastDfsUtils {

    public VcVideo uploadVideo(InputStream inputStream,String fileName,String fileExt)
    {
        try {
            String fileId = uploadFile(inputStream, fileName, fileExt);

            VcVideo vcVideo = new VcVideo();
            vcVideo.setName(fileName);
            vcVideo.setSrc(fileId);
            vcVideo.setIntro(fileExt);

            return vcVideo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Pic uploadPic(InputStream inputStream, String fileName, String fileExt)
    {
        try {
            String fileId = uploadFile(inputStream, fileName, fileExt);

            Pic pic = new Pic();
            pic.setPicext(fileExt);
            pic.setSrc(fileId);
            pic.setType(0);

            return pic;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public String uploadFile(InputStream inputStream,String fileName,String fileExt)
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

            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", fileName);


            byte[] movie = new byte[inputStream.available()];
            inputStream.read(movie);

            String fileId = client.upload_file1(movie, fileExt, metaList);

            System.out.println("upload success. file id is: " + fileId);

            trackerServer.close();
            return fileId;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
