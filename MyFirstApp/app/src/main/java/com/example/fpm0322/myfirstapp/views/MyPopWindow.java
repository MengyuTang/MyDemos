package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.fpm0322.myfirstapp.R;

public class MyPopWindow {

    private Context mContext;

    private PopupWindow window;

    private RecyclerView.Adapter adapter;
    public MyPopWindow(Context mContext){
        this.mContext = mContext;
    }

    public void showPopWindow(View baseView){
        View view = LayoutInflater.from(mContext).inflate(R.layout.popwindow_layout,null);
        window = new PopupWindow(view, 400,300,true);
        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_rectangle_of_circle_corner_white));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        RecyclerView ryDemo = view.findViewById(R.id.ry_demo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        ryDemo.setLayoutManager(layoutManager);
        ryDemo.setAdapter(adapter);
        window.showAsDropDown(baseView,baseView.getWidth()/2 - 200,2, Gravity.BOTTOM);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        this.adapter = adapter;
    }

    /**
     * 隐藏window
     */
    public void dismiss(){
        if (null != window){
            window.dismiss();
        }
    }
}
