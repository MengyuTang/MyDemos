package com.example.fpm0322.mvpdemo.presenter.inter;

import com.example.fpm0322.mvpdemo.view.inter.IView;

import java.util.HashMap;

public interface IPresenter {

    void getData(HashMap<String,String> map);

    void attachView(IView view);

    void detachView();
}
