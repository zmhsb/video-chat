package com.example.webmedia.model;

import java.util.List;

public class VcUserRole {
    private Long uid;

    private Long rid;

    VcRole role;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "VcUserRole{" +
                "uid=" + uid +
                ", rid=" + rid +
                ", roles=" + role +
                '}';
    }

    public VcRole getRoles() {
        return role;
    }

    public void setRoles(VcRole role) {
        this.role = role;
    }
}