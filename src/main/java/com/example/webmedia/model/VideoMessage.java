package com.example.webmedia.model;

public class VideoMessage {
    private Integer sign;
    private Integer id;
    private String src;
    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public VideoMessage() {
    }

    public VideoMessage(Integer sign, Integer id) {
        this.sign = sign;
        this.id = id;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
