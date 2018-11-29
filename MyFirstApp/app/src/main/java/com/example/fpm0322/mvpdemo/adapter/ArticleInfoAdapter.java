package com.example.fpm0322.mvpdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fpm0322.mvpdemo.model.DatasBean;
import com.example.fpm0322.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleInfoAdapter extends RecyclerView.Adapter<ArticleInfoAdapter.ArticleInfoHolder> {

    private List<DatasBean> datas = new ArrayList<>();

    private Context mContext;

    public ArticleInfoAdapter(Context context){
        mContext = context;
    }

    public void setDatas(List<DatasBean> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ArticleInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleInfoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_string,parent,false));
    }

    @Override
    public void onBindViewHolder(ArticleInfoHolder holder, int position) {
        if (datas.size()>0){
            holder.textView.setText(datas.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ArticleInfoHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textview)
        TextView textView;

        public ArticleInfoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
