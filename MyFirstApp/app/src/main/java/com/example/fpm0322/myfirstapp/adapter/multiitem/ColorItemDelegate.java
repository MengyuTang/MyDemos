package com.example.fpm0322.myfirstapp.adapter.multiitem;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fpm0322.myfirstapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorItemDelegate extends SuperDelegate<ColorItemDelegate.ViewHolder> {

    private int color;

    private Context mContext;

    public ColorItemDelegate(Context context){
        super();
        this.mContext = context;
    }

    public void setColor(int color){
        this.color = color;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.ColorItem;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_color,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //以下代码防止View重复刷新
        if (!uiFlag) {
            return;
        }
        uiFlag = false;
        //从此处开始刷新UI
        holder.textView.setBackgroundResource(color);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textview)
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
