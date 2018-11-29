package com.example.fpm0322.myfirstapp.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.adapter.multiitem.PopRecyclerAdapter;
import com.example.fpm0322.myfirstapp.utils.CommonUtils;
import com.example.fpm0322.myfirstapp.views.MyPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class TestActivity extends AppCompatActivity {

    private String TAG = "TestActivity";

    @BindView(R.id.btn_show_window)
    Button btnShowWindow;

    @BindView(R.id.et_test)
    EditText etTest;

    private List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initDatas();
        initView();
        Intent i = new Intent();
        i.setAction("com.foconn.fnfactoring.fnconnnew.receiver.AlarmReceiver");
        i.setComponent(new ComponentName("com.foconn.fnfactoring.fnconnnew","com.foconn.fnfactoring.fnconnnew.receiver.AlarmReceiver"));
        sendBroadcast(i);
    }

    private void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("测试"+(i+1));
        }
        String num = CommonUtils.keep2Num("12345");
        String num1 = CommonUtils.keep2Num("12346.0");
        String num2 = CommonUtils.keep2Num("12347.12");
        Log.e(TAG,"num="+num);
        Log.e(TAG,"num1="+num1);
        Log.e(TAG,"num2="+num2);
    }

    private void initView() {
        btnShowWindow.setOnClickListener(v -> {
            MyPopWindow window = new MyPopWindow(this);
            PopRecyclerAdapter adapter = new PopRecyclerAdapter(this);
            adapter.setDatas(datas);
            adapter.setmOnItemClickListener(position -> {
                window.dismiss();
                btnShowWindow.setText(datas.get(position));
            });
            window.setAdapter(adapter);
            window.showPopWindow(btnShowWindow);
        });

        etTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())){
                    if (!CommonUtils.account.equals(s.toString().trim().replaceAll(",",""))){
                        etTest.setText(CommonUtils.addComma(s.toString().trim().replaceAll(",",""),etTest));
                        etTest.setSelection(CommonUtils.addComma(s.toString().trim().replaceAll(",",""),etTest).length());
                    }
                }
            }
        });
    }


}
