package com.example.fpm0322.myfirstapp.bean;

import java.util.List;

public class BaseBean {
    private String tag1;
    private String tag2;
    private String totalNum;
    private String start_index;
    private String return_number;
    private List<ImageBean> data;

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getStart_index() {
        return start_index;
    }

    public void setStart_index(String start_index) {
        this.start_index = start_index;
    }

    public String getReturn_number() {
        return return_number;
    }

    public void setReturn_number(String return_number) {
        this.return_number = return_number;
    }

    public List<ImageBean> getData() {
        return data;
    }

    public void setData(List<ImageBean> data) {
        this.data = data;
    }
}
