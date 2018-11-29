package com.example.fpm0322.myfirstapp.adapter.multiitem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author Zhenhua on 2017/5/9 16:35.
 * @email zhshan@ctrip.com
 */

public abstract class SuperDelegate<VH extends RecyclerView.ViewHolder> {
    public boolean uiFlag = false;

    public ViewHolderType type = getViewHolderType();

    public abstract ViewHolderType getViewHolderType();

    public abstract int getItemViewType(int position);

    @NonNull
    public abstract VH onCreateViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(final VH holder,final int position);
}
