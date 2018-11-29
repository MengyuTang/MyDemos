package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomImgContainer extends ViewGroup {
    public CustomImgContainer(Context context) {
        this(context,null);
    }

    public CustomImgContainer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImgContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //计算所有子View的宽高
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //viewGroup的宽度和高度
        int mWidth;
        int mHeight;

        //上下子View的宽度和左右子View的高度
        int tWidth =0;
        int bWidth = 0;
        int lHeight = 0;
        int rHeight = 0;
        //遍历所有子View，求左右两边的高度和上下两边的宽度
        int childCount = getChildCount();
        if (childCount>0){
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();
                MarginLayoutParams cParams = (MarginLayoutParams) child.getLayoutParams();
                //上面两个子View
                if (i == 0 || i == 1){
                    tWidth += cWidth+cParams.leftMargin+cParams.rightMargin;
                }
                //左边两个子View
                if (i == 0 || i==2){
                    lHeight += cHeight + cParams.topMargin+cParams.bottomMargin;
                }
                //下面两个子View
                if (i == 2 || i == 3){
                    bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
                }
                //右边两个子View
                if (i == 1 || i==3){
                    rHeight = cHeight+ cParams.topMargin+cParams.leftMargin;
                }
            }
        }
        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }else{
            mWidth = Math.max(tWidth,bWidth);
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else{
            mHeight = Math.max(lHeight,rHeight);
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    /**
     * 对所有子View进行定位
     * @param changed
     * @param l 左
     * @param t 上
     * @param r 右
     * @param b 下
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount>0){
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int cWidth = child.getMeasuredWidth();
                int cHeight = child.getMeasuredHeight();
                MarginLayoutParams cParams = (MarginLayoutParams) child.getLayoutParams();
                //子View的margin
                int cl,ct,cr,cb;
                cl = 0;
                ct = 0;
                switch(i){
                    //左上角
                    case 0:
                        cl = cParams.leftMargin;
                        ct = cParams.topMargin;
                        break;

                        //右上角
                    case 1:
                        cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
                        ct = cParams.topMargin;
                        break;

                        //左下角
                    case 2:
                        cl = cParams.leftMargin;
                        ct = getHeight() - cHeight - cParams.bottomMargin;
                        break;

                    case 3:
                        cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
                        ct = getHeight() - cHeight - cParams.bottomMargin;
                        break;
                }
                cr = cl+cWidth;
                cb = ct+cHeight;
                child.layout(cl,ct,cr,cb);
            }
        }
    }
}
