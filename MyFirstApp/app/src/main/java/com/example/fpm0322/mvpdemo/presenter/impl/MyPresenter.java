package com.example.fpm0322.mvpdemo.presenter.impl;

import com.example.fpm0322.mvpdemo.model.ArticleInfo;
import com.example.fpm0322.mvpdemo.model.MyObservable;
import com.example.fpm0322.mvpdemo.model.impl.MyModel;
import com.example.fpm0322.mvpdemo.presenter.inter.IPresenter;
import com.example.fpm0322.mvpdemo.view.inter.IView;

import java.util.HashMap;

import io.reactivex.Observer;

public class MyPresenter implements IPresenter{

    private IView view;

    @Override
    public void getData(HashMap<String,String> map){
        view.showLoading();
        new MyModel().getData(map, new MyObservable() {

            @Override
            protected void subscribeActual(Observer<? super ArticleInfo> observer) {

            }

            @Override
            public void onSuccess(ArticleInfo data) {
                view.showData(data);
            }

            @Override
            public void onFailed(String errMsg) {
                view.showErrorMessage(errMsg);
            }

            @Override
            public void onError() {
                view.showErrorMessage("");
            }

            @Override
            public void onComplete() {
                view.hideLoading();
            }
        });
    }

    @Override
    public void attachView(IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (null != view){
            view = null;
        }
    }
}
