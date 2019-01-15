package com.material.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 2147826905309298500L;
    private String res;
    private String desc;
    private T data;

    public Result() {
    }

    public Result(String res, String desc, T data){
        this.res = res;
        this.desc = desc;
        this.data = data;
    }
    public Result(String res, String desc) {
        this.res = res;
        this.desc = desc;
    }
    public String getRes() {
        return this.res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}