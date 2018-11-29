package com.example.fpm0322.myfirstapp.adapter.multiitem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fpm0322.myfirstapp.R;

import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_COLOR,
        ITEM_TYPE_TEXT
    }
    private List<String> list;
    private int color;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    public StringAdapter(Context mContext){
        super();
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<String> list,int color){
        this.list = list;
        this.color = color;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0){
            return new ColorHolder(mLayoutInflater.inflate(R.layout.item_color,viewGroup,false));
        }else{
            return new StringHolder(mLayoutInflater.inflate(R.layout.item_string,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ColorHolder){
            ((ColorHolder) viewHolder).textView.setBackgroundResource(color);
        }else if (viewHolder instanceof StringHolder){
            if (null != list && list.size()>0){
                ((StringHolder) viewHolder).textView.setText(list.get(i-1));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_COLOR.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        if (null == list){
            return 0;
        }
        return list.size();
    }

    public class StringHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public StringHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

    public class ColorHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ColorHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
