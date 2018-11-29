package com.example.fpm0322.myfirstapp;

import android.graphics.Bitmap;

import com.example.fpm0322.myfirstapp.bean.BaseBean;
import com.example.fpm0322.myfirstapp.bean.ImageBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/channel/listjson")
    public Call<BaseBean> getImageUrls(@Query("pn")String pn, @Query("rn")String rn, @Query("tag1")String tag1, @Query("tag2")String tag2, @Query("ie")String ie);

    @GET
    public Observable<ResponseBody> getImages();
}
