package com.example.fpm0322.myfirstapp.adapter.multiitem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Zhenhua on 2017/5/9 16:34.
 * @email zhshan@ctrip.com
 */

public class AdapterViewHolderManager<VH extends RecyclerView.ViewHolder> {
    private List<SuperDelegate> delegates;

    public void setDelegates(List<SuperDelegate> delegates) {
        this.delegates = delegates;
    }

    public int getItemViewType(int position) {
        if (delegates != null) {
            SuperDelegate delegate = delegates.get(position);
            if (delegate != null) {
                return delegate.getItemViewType(position);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public void setRangeRenderEnable(int position, int size){
        for(int n = position; n < size; n++){
            delegates.get(n).uiFlag = true;
        }
    }

    public void setItemRenderEnable(int position) {
        delegates.get(position).uiFlag = true;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        SuperDelegate delegate = getDelegateFromViewType(ViewType);
        if (delegate != null) {
            return delegate.onCreateViewHolder(parent);
        }
        return null;
    }

    public void onBindViewHolder(@NonNull VH viewHolder,int position) {
        SuperDelegate delegate = getDelegateFromViewType(position);
        if (delegate != null) {
            delegate.onBindViewHolder(viewHolder,position);
        }
    }

    public SuperDelegate getDelegateFromViewType(int viewType) {
        return delegates.get(viewType);
    }

    public int getItemCount() {
        return delegates.size();
    }
}
