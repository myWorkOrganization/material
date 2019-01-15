package com.material.entity.menu;

import java.util.List;

public class Tree{
    private int id;
    private String text;
    private String state = "open";
    private boolean checked = false;
    private Object attributes;
    private List<Tree> children;
    private String iconCls;
    private int pid;
    private String url;

    public int getId()
    {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<Tree> getChildren() {
        return this.children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}