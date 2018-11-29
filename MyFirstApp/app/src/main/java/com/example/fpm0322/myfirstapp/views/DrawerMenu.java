package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

public class DrawerMenu extends ViewGroup implements View.OnClickListener {
    public DrawerMenu(Context context) {
        this(context,null);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int mButtonWidth;
    private int mButtonHeight;
    private int mButtonX;
    private int mButtonY;
    private View mButton_bottom;
    private Boolean mIsShowing = false;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        if (count>0){
            layoutButtom();
            if (mIsShowing){
                for (int i = 1; i < count; i++) {
                    View child = getChildAt(i);
                    int childWidth = child.getMeasuredWidth();
                    child.layout(0,mButtonY - mButtonHeight*i,childWidth,mButtonY - mButtonHeight*(i-1));
                    child.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void layoutButtom() {
        mButton_bottom = getChildAt(0);
        mButton_bottom.setOnClickListener(this);
        mButtonWidth = mButton_bottom.getMeasuredWidth();
        mButtonHeight = mButton_bottom.getMeasuredHeight();
        mButtonX = 0;
        mButtonY = getMeasuredHeight() - mButtonHeight;
        mButton_bottom.layout(mButtonX,mButtonY,mButtonWidth,getMeasuredHeight());
    }

    @Override
    public void onClick(View v) {
        int count = getChildCount();
        if (mIsShowing) {
            for (int i = 1; i < count; i++) {
                View child = getChildAt(i);
                    TranslateAnimation anim = new TranslateAnimation(0, -child.getMeasuredWidth(), 0, 0);
                    anim.setDuration(1000 + i * 100);
                    child.startAnimation(anim);
                    child.setVisibility(View.GONE);
            }
            mIsShowing = false;
        } else {
            for (int i = 1; i < count; i++) {
                View child = getChildAt(i);
                    TranslateAnimation anim = new TranslateAnimation(-child.getMeasuredWidth(), 0, 0, 0);
                    anim.setDuration(1000 + i * 100);
                    child.startAnimation(anim);
                    child.setVisibility(View.VISIBLE);
            }
            mIsShowing = true;
        }
    }
}
