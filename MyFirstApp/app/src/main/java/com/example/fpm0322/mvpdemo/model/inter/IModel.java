package com.example.fpm0322.mvpdemo.model.inter;

import com.example.fpm0322.mvpdemo.model.MyObservable;

import java.util.HashMap;

public interface IModel {
    void getData(final HashMap<String,String> map, final MyObservable callback);
}
