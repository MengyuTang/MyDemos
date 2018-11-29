package com.example.fpm0322.myfirstapp.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.fpm0322.myfirstapp.R;

public class PopView extends View {

    /**
     * 内容
     */
    private String mContent;

    /**
     * 绘制内容
     */
    private Rect mBound;

    /**
     * 背景色
     */
    private int mBackground;

    /**
     * 字号
     */
    private int mContentTextSize;

    private Paint mPaint;
    public PopView(Context context) {
        this(context,null);
    }

    public PopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PopView,defStyleAttr,0);
        int num = typedArray.getIndexCount();
        if (num>0){
            for (int i = 0; i < num; i++) {
                int attr = typedArray.getIndex(i);
                switch (attr){
                    case R.styleable.PopView_backgroundColor:
                        mBackground = typedArray.getColor(attr, Color.parseColor("#2C97DE"));
                        break;

                    case R.styleable.PopView_content:
                        mContent = typedArray.getString(attr);
                        break;

                    case R.styleable.PopView_contentTextSize:
                        mContentTextSize = typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                        break;
                }
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mContentTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mContent,0,mContent.length(),mBound);
    }

    private int mRectWidth;

    private int mRectHeight;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth;
        int mHeight;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize+30;
            mRectWidth = mWidth - getPaddingLeft() - getPaddingRight();
        }else{
//            mPaint.setTextSize(mContentTextSize);
//            mPaint.getTextBounds(mContent,0,mContent.length(),mBound);
            float textWidth = mBound.width();
            mRectWidth = (int) (textWidth + 30);
            mWidth = getPaddingLeft()+mRectWidth+getPaddingRight()+30;
        }

        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize+30;
            mRectHeight = mHeight - getPaddingTop() - getPaddingBottom();
        }else{
//            mPaint.setTextSize(mContentTextSize);
//            mPaint.getTextBounds(mContent,0,mContent.length(),mBound);
            float textHeight = mBound.height();
            mRectHeight = (int) (textHeight + 30); //文本高度加上下padding15
            mHeight = getPaddingBottom()+getPaddingTop()+mRectHeight+30; //矩形高度+padding+三角形高度
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制矩形
        mPaint.setColor(mBackground);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        RectF mRect = new RectF(15,15,getWidth()-15,mRectHeight+15);
        canvas.drawRoundRect(mRect,10,10,mPaint);
        mPaint.setColor(Color.WHITE);
        //绘制内容
        canvas.drawText(mContent,mRectWidth/2-mBound.width()/2 -15,mRectHeight/2 + mBound.height()/2 + 7,mPaint);
        mPaint.setColor(mBackground);
        Path path = new Path();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        path.moveTo(mRectWidth/2-30,mRectHeight);
        path.lineTo(mRectWidth/2,mRectHeight+30);
        path.lineTo(mRectWidth/2+30,mRectHeight);
        path.close();
        canvas.drawPath(path,mPaint);
    }
}
