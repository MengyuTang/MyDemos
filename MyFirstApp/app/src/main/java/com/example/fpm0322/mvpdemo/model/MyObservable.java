package com.example.fpm0322.mvpdemo.model;

import io.reactivex.Observable;

public abstract class MyObservable extends Observable<ArticleInfo> {
    public abstract void onSuccess(ArticleInfo data);

    public abstract void onFailed(String errMsg);

    public abstract void onError();

    public abstract void onComplete();

}
