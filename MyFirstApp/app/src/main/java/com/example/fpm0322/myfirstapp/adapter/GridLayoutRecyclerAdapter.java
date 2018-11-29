package com.example.fpm0322.myfirstapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.bean.MenuItemBean;

import java.util.List;

public class GridLayoutRecyclerAdapter extends RecyclerView.Adapter<GridLayoutRecyclerAdapter.ViewHolder>{

    private List<MenuItemBean> menuItems;

    public void setDatas(List<MenuItemBean> menuItems){
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_menu,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        if (null != menuItems && menuItems.size()>0){
            MenuItemBean itemBean = menuItems.get(i);
            holder.tvMenuItem.setText(itemBean.getItemName());
            if(itemBean.isItemState()){
                //如果处于编辑状态，则在左上角显示小叉号
                holder.fmDelete.setVisibility(View.VISIBLE);
                holder.fmDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        menuItems.remove(i);
                        notifyDataSetChanged();
                    }
                });
            }else{
                holder.fmDelete.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMenuItem;

        FrameLayout fmDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenuItem = itemView.findViewById(R.id.tv_menu_item);
            fmDelete = itemView.findViewById(R.id.fm_delete);
        }
    }
}
