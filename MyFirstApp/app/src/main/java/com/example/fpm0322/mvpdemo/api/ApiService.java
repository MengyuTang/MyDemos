package com.example.fpm0322.mvpdemo.api;

import com.example.fpm0322.mvpdemo.model.ArticleInfo;

import io.reactivex.Observable;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface ApiService {

    @HTTP(method = "GET",path = "/article/listproject/{page}/json",hasBody = false)
    Observable<ArticleInfo> getArticleInfo(@Path("page") int page);
}
