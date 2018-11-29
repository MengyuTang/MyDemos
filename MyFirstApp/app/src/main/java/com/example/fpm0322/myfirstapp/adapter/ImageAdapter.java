package com.example.fpm0322.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.fpm0322.myfirstapp.Api;
import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.bean.ImageBean;
import com.example.fpm0322.myfirstapp.imageutils.BitCache;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<ImageBean> datas;

    private Context context;

    private Api mApi;

    public ImageAdapter(Context context,List<ImageBean> datas){
        super();
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_list_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        if (null != datas && datas.size()>0){
            ImageBean imageBean = datas.get(i);
            try {
                if (null != imageBean){
                    initImageLoader(holder.imageView,imageBean.getImage_url());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    public void initImageLoader(ImageView iv,String url){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageLoader loader = new ImageLoader(requestQueue,new BitCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        loader.get(url,listener,200,200);
    }

}
