package com.example.fpm0322.myfirstapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.adapter.ImageAdapter;
import com.example.fpm0322.myfirstapp.bean.ImageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String TAG = "ImageListActivity";
    private List<ImageBean> datas;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ButterKnife.bind(this);
        initDatas();
    }

    public void initDatas() {
        datas = (List<ImageBean>) getIntent().getExtras().getSerializable("datas");
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        if (null != datas && datas.size() > 0) {
            adapter = new ImageAdapter(this, datas);
            recyclerView.setAdapter(adapter);
        }
    }

}
