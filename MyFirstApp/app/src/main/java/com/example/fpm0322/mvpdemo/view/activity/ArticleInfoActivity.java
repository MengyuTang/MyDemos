package com.example.fpm0322.mvpdemo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.fpm0322.mvpdemo.adapter.ArticleInfoAdapter;
import com.example.fpm0322.mvpdemo.model.ArticleInfo;
import com.example.fpm0322.mvpdemo.presenter.impl.MyPresenter;
import com.example.fpm0322.mvpdemo.view.inter.IView;
import com.example.fpm0322.myfirstapp.R;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleInfoActivity extends AppCompatActivity implements IView{

    private MyPresenter myPresentor;

    private ArticleInfoAdapter adapter;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;

    @BindView(R.id.ry_article_Info)
    RecyclerView ryArticleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_info);
        ButterKnife.bind(this);
        initRecyclerView();
        initView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ryArticleInfo.setLayoutManager(layoutManager);
        adapter = new ArticleInfoAdapter(this);
        ryArticleInfo.setAdapter(adapter);
    }

    private void initView() {
        myPresentor = new MyPresenter();
        myPresentor.attachView(this);
    }

    @OnClick(R.id.btn_query)
    void getDatas(){
        HashMap<String,String> map = new HashMap<>();
        map.put("page","0");
        myPresentor.getData(map);
    }

    @Override
    public void showData(ArticleInfo data) {
        Log.e("ArticleInfoActivity", new Gson().toJson(data));
        adapter.setDatas(data.getData().getDatas());
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String msg) {
        Log.e(this.getClass().getSimpleName(),"msg:"+msg);
    }
}
