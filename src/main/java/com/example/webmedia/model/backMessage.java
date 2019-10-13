package com.example.webmedia.model;


/**
* @program: BackMessage.java
*
* @author: xty
*
* @create: 2019/10/13/013
**/

public class BackMessage {

    private Integer sign;
    private String role;
    private String username;
    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public BackMessage() {
    }

    public BackMessage(Integer sign, String role, String username) {
        this.sign = sign;
        this.role = role;
        this.username = username;
    }

    public static BackMessage buildSuccess()
    {
        BackMessage backMessage = new BackMessage();
        backMessage.setSign(200);
        backMessage.setContent("success");
        return backMessage;
    }

    public static BackMessage buildFail()
    {
        BackMessage backMessage = new BackMessage();
        backMessage.setSign(404);
        backMessage.setContent("Fail");
        return backMessage;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
