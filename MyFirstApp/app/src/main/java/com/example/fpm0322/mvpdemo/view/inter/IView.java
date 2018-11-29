package com.example.fpm0322.mvpdemo.view.inter;

import com.example.fpm0322.mvpdemo.model.ArticleInfo;

public interface IView {

    void showData(ArticleInfo data);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String msg);

}
