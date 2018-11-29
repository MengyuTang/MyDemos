package com.example.fpm0322.myfirstapp.adapter.multiitem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fpm0322.myfirstapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringItemDelegate extends SuperDelegate<StringItemDelegate.ViewHolder> {

    private List<String> list;

    private Context mContext;

    private StringAdapter adapter;

    public StringItemDelegate(Context context){
        super();
        this.mContext = context;
    }

    public void setDatas(List<String> list){
        this.list = list;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.StringItem;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d("StringItemDelegate","onCreateViewHolder");
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_string_new,parent,false);
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
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ry_list)
        RecyclerView ryList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
