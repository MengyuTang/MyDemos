package com.example.fpm0322.myfirstapp.bean;

public class TestBean {

    private String name;

    private String age;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age == null ? "" : age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
