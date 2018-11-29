package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.fpm0322.myfirstapp.R;


public class CheckView extends View {

    /**
     * 画笔
     */
    private Paint mPaint;

    private Rect mRect;

    /**
     * 外圈圆环颜色
     */
    private int mOuterCircleColor;

    /**
     * 内圈圆点颜色
     */
    private int mInnerCircleColor;

    /**
     * 外圈圆半径
     */
    private float mOuterCircleRadius;

    private boolean isChecked;

    public CheckView(Context context) {
        this(context,null);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray td = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckView,defStyleAttr,0);
        int count = td.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = td.getIndex(i);
            switch(attr){
                case R.styleable.CheckView_outer_circle_color:
                    mOuterCircleColor = td.getInt(attr, Color.parseColor("#b1b1b1"));
                    break;
                case R.styleable.CheckView_inner_circle_color:
                    mInnerCircleColor = td.getInt(attr, Color.BLACK);
                    break;
                case R.styleable.CheckView_outer_radius:
                    mOuterCircleRadius = td.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CheckView_is_checked:
                    isChecked = td.getBoolean(attr,false);
                    break;
            }
        }
        td.recycle();
        mPaint = new Paint();
        mRect = new Rect(0,0,(int) mOuterCircleRadius*2+5,(int)mOuterCircleRadius*2+5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth;
        int mHeight;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }else{
            mWidth = mRect.width()+getPaddingLeft()+getPaddingRight();
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else{
            mHeight = mRect.height()+getPaddingBottom()+getPaddingRight();
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 先画外圈
         */
        mPaint.setColor(mOuterCircleColor);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,mOuterCircleRadius,mPaint);

        /**
         * 绘制内圈
         */
        if (isChecked){
            mPaint.setColor(mInnerCircleColor);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth()/2,getHeight()/2,mOuterCircleRadius/2,mPaint);
        }
//        mPaint.setColor(mInnerCircleColor);
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(getWidth()/2,getHeight()/2,mOuterCircleRadius/2,mPaint);
    }

    public void setChecked(boolean checked){
        isChecked = checked;
        invalidate();
    }

    public boolean getChecked(){
        return isChecked;
    }
}
