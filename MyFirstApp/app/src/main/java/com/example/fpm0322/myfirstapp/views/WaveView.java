package com.example.fpm0322.myfirstapp.views;

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
import android.view.ViewGroup;

import com.example.fpm0322.myfirstapp.R;

public class WaveView extends View {

    /**
     * 波浪数量
     */
    private int mWaveCount;

    /**
     * 波浪宽度
     */
    private int mWaveWidth;

    /**
     * 波浪形状
     */
    private int mode;
    /**
     * 背景颜色
     */
    private int bg;

    /**
     * 三角形波浪
     */
    private final int WAVE_TRIANGLE = -2;

    /**
     * 圆形波浪
     */
    private final int WAVE_CIRCLE = -1;

    private Paint mPaint;

    /**
     * 内容
     */
    private String mContent;

    /**
     * 内容字体颜色
     */
    private int mContentColor;

    /**
     * 内容字号
     */
    private int mContentTextSize;

    /**
     * 内容容器
     */
    private Rect mBound;
    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaveView,defStyleAttr,0);
        int num = ta.getIndexCount();
        if(num>0){
            for (int i = 0; i < num; i++) {
                int attr = ta.getIndex(i);
                switch(attr){
                    case R.styleable.WaveView_waveCount:
                        mWaveCount = ta.getInteger(attr,1);
                        break;

                    case R.styleable.WaveView_waveWidth:
                        mWaveWidth = ta.getInteger(attr,10);
                        break;

                    case R.styleable.WaveView_mode:
                        mode = ta.getInt(attr,WAVE_TRIANGLE);
                        break;

                    case R.styleable.WaveView_content:
                        mContent = ta.getString(attr);
                        break;
                    case R.styleable.WaveView_contentTextSize:
                        mContentTextSize = ta.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,
                                getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.WaveView_titleTextColor:
                        mContentColor = ta.getColor(attr, Color.WHITE);
                        break;
                    case R.styleable.WaveView_android_color:
                        bg = ta.getColor(attr,Color.YELLOW);
                        break;
                }
            }
        }
        ta.recycle();
        mPaint = new Paint();
        mBound = new Rect();
        mPaint.setTextSize(mContentTextSize);
        mPaint.getTextBounds(mContent,0,mContent.length(),mBound);
    }

    /**
     * 矩形宽度
     */
    private int mRectWidth;

    /**
     * 矩形高度
     */
    private int mRectHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth;
        int mHeight;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            //指定了宽度
            mWidth = widthSize;
            mRectWidth = widthSize - getPaddingLeft() - getPaddingRight() - 2*mWaveWidth;
        }else{
            //未指定宽度，默认为wrap_content,则根据内容确定大小
            float textWidth = mBound.width();
            mRectWidth = (int) (textWidth+20); //矩形宽度设置为内容宽度+左右padding 10
            mWidth = mRectWidth + getPaddingLeft()+getPaddingRight()+2*mWaveWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
            mRectHeight = heightSize - getPaddingTop() - getPaddingBottom();
        }else{
            float textHeight = mBound.height()*(mRectWidth - 2*mWaveWidth)/mBound.width();//文本高度等于一行的文本高度*行数
            mRectHeight = (int) (textHeight + 20);//矩形高度等于文本高度+上下padding 10
            mHeight = mRectHeight+ getPaddingBottom()+getPaddingTop();
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //先绘制矩形
        mPaint.setColor(bg);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        RectF rectF = new RectF(getPaddingLeft()+mWaveWidth,0,getWidth() - getPaddingRight() - mWaveWidth,getHeight() - getPaddingBottom());
        canvas.drawRect(rectF,mPaint);
        //在绘制左右边的波浪
        int mWaveHeight = mRectHeight/mWaveCount;
        if (mode == WAVE_TRIANGLE){
            //三角形波浪
            //先计算绘制的波浪高度
            Path path = new Path();
            Path path1 = new Path();
            for (int i = 0; i < mWaveCount; i++) {
                path.moveTo(mWaveWidth+getPaddingLeft(),i*mWaveHeight+getPaddingTop());
                path.lineTo(getPaddingLeft(),mWaveHeight*(2*i+1)/2+getPaddingTop());
                path.lineTo(mWaveWidth+getPaddingLeft(),(i+1)*mWaveHeight+getPaddingTop());
                canvas.drawPath(path,mPaint);
                path1.moveTo(getWidth() - getPaddingRight() - mWaveWidth,i*mWaveHeight+getPaddingTop());
                path1.lineTo(getWidth() - getPaddingRight(),mWaveHeight*(2*i+1)/2+getPaddingTop());
                path1.lineTo(getWidth() - getPaddingRight() - mWaveWidth,(i+1)*mWaveHeight+getPaddingTop());
                canvas.drawPath(path1,mPaint);
            }
        }else if (mode == WAVE_CIRCLE){
            //弧形波浪
//            for (int i = 0; i < mWaveCount; i++) {
//                RectF rf = new RectF(getPaddingLeft(),i*mWaveHeight+getPaddingTop(),mWaveWidth+getPaddingLeft(),(i+1)*mWaveHeight+getPaddingTop());
//                canvas.drawArc(rf,90,270,false,mPaint);
//                RectF rf1 = new RectF(getWidth() - getPaddingRight() - mWaveWidth,i*mWaveHeight+getPaddingTop(),getWidth() - getPaddingRight(),(i+1)*mWaveHeight+getPaddingTop());
//                canvas.drawArc(rf1,-90,90,false,mPaint);
//            }
        }

        //最后绘制文本内容
        mPaint.setColor(mContentColor);
        mPaint.setTextSize(mContentTextSize);
        canvas.drawText(mContent,getPaddingLeft()+mWaveWidth+10,getPaddingTop()+10+mBound.height()/2,mPaint);
    }
}
