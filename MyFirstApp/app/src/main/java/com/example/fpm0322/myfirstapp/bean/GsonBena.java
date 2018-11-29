package com.example.fpm0322.myfirstapp.bean;

public class GsonBena {

    /**
     * code : 0
     * data : {}
     * msg : string
     * randomKey : string
     * randomStragy : string
     */

    private int code;
    private DataBean data;
    private String msg;
    private String randomKey;
    private String randomStragy;

    public int getCode() {
        return code;
    }

    public DataBean getData() {
        return data;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getRandomKey() {
        return randomKey == null ? "" : randomKey;
    }

    public String getRandomStragy() {
        return randomStragy == null ? "" : randomStragy;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public void setRandomStragy(String randomStragy) {
        this.randomStragy = randomStragy;
    }
}
