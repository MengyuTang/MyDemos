package com.example.fpm0322.myfirstapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.adapter.multiitem.ColorItemDelegate;
import com.example.fpm0322.myfirstapp.adapter.multiitem.MainAdapter;
import com.example.fpm0322.myfirstapp.adapter.multiitem.StringAdapter;
import com.example.fpm0322.myfirstapp.adapter.multiitem.StringItemDelegate;
import com.example.fpm0322.myfirstapp.adapter.multiitem.SuperDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiItemRecyclerActivity extends AppCompatActivity {

    @BindView(R.id.ry_list)
    RecyclerView ryList;

    private StringAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item_recycler);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 6; j++) {
                list.add("2018年" + i + "月" + j + "日");
            }
        }
        adapter = new StringAdapter(this);
        adapter.setDatas(list, R.color.colorAccent);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ryList.setLayoutManager(layoutManager);
        ryList.setAdapter(adapter);
    }

}
