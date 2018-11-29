package com.example.fpm0322.mvpdemo.model.impl;

import android.util.Log;

import com.example.fpm0322.mvpdemo.api.RetrofitManager;
import com.example.fpm0322.mvpdemo.model.ArticleInfo;
import com.example.fpm0322.mvpdemo.model.MyObservable;
import com.example.fpm0322.mvpdemo.model.inter.IModel;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyModel implements IModel{

    private RetrofitManager mManager = new RetrofitManager();

    @Override
    public void getData(HashMap<String, String> map, MyObservable callback) {
        String page = map.get("page");
        new Thread(new Runnable() {
            @Override
            public void run() {
                mManager.getArticleInfo(Integer.parseInt(page), new Observer<ArticleInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ArticleInfo articleInfo) {
                        callback.onSuccess(articleInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
            }
        }).start();

    }
}
