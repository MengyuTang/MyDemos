package com.example.fpm0322.myfirstapp.adapter.multiitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fpm0322.myfirstapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopRecyclerAdapter extends RecyclerView.Adapter<PopRecyclerAdapter.StringHolder> {

    private Context mContext;

    private List<String> datas;

    private MyOnItemClickListener mOnItemClickListener;

    public PopRecyclerAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setDatas(List<String> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public StringHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StringHolder(LayoutInflater.from(mContext).inflate(R.layout.item_string,parent,false));
    }

    @Override
    public void onBindViewHolder(StringHolder holder, int position) {
        if (datas.size()>0){
            holder.textView.setText(datas.get(position));
            holder.textView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class StringHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textview)
        TextView textView;

        public StringHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface MyOnItemClickListener{
        void onClick(int position);
    }

    public void setmOnItemClickListener(MyOnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
