package com.example.webmedia.model;

public class Pic {
    private Integer id;

    private String src;

    private String picext;

    private Integer type;

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

    public String getPicext() {
        return picext;
    }

    public void setPicext(String picext) {
        this.picext = picext;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", src=").append(src);
        sb.append(", picext=").append(picext);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }
}