package com.example.webmedia.model;

public class backMessage {

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

    public backMessage() {
    }

    public backMessage(Integer sign, String role, String username) {
        this.sign = sign;
        this.role = role;
        this.username = username;
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
