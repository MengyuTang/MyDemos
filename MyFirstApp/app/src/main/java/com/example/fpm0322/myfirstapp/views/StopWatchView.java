package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.fpm0322.myfirstapp.R;

/**
 * 秒表
 */
public class StopWatchView extends View {

    /**
     * 秒针长度
     */
    private float mSecondPointerLength;
    /**
     * 分针长度
     */
    private float mMinutePointerLength;
    /**
     * 秒刻度长度
     */
    private float mSecondScaleLength;
    /**
     * 分刻度长度
     */
    private float mMinuteScaleLength;
    /**
     * 子刻度长度
     */
    private float mChildSecondScaleLength;
    /**
     * 指针颜色
     */
    private int mPointerColor;
    /**
     * 背景颜色
     */
    private int mBackground;
    private Paint mPaint;
    private Rect mRect;
    public StopWatchView(Context context) {
        this(context,null);
    }

    public StopWatchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StopWatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray td = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StopWatchView, defStyleAttr, 0);
        if (td.getIndexCount()>0){
            for (int i = 0; i < td.getIndexCount(); i++) {
                int attr = td.getIndex(i);
                switch (attr){
                    case R.styleable.StopWatchView_secondsPointerLength:
                        mSecondPointerLength = td.getFloat(attr,10.0f);
                        break;
                    case R.styleable.StopWatchView_minutePointerLength:
                        mMinutePointerLength = td.getFloat(attr,2.0f);
                        break;
                    case R.styleable.StopWatchView_secondScaleLength:
                        mSecondScaleLength = td.getFloat(attr,2.0f);
                        break;
                    case R.styleable.StopWatchView_minuteScaleLength:
                        mMinuteScaleLength = td.getFloat(attr,2.0f);
                        break;
                    case R.styleable.StopWatchView_childSecondScaleLength:
                        mChildSecondScaleLength = td.getFloat(attr,2.0f);
                        break;
                    case R.styleable.StopWatchView_pointerColor:
                        mPointerColor = td.getColor(attr, Color.YELLOW);
                        break;
                    case R.styleable.StopWatchView_android_background:
                        mBackground = td.getColor(attr,Color.BLACK);
                        break;
                }
            }
        }
        td.recycle();
        mPaint = new Paint();
        mRect = new Rect(0,0,(int)mSecondPointerLength+10,(int)mSecondPointerLength+10);
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
        //绘制背景
        mPaint.setColor(mBackground);
        canvas.drawRect(mRect,mPaint);
        //绘制表盘
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        //绘制秒针
        mPaint.setColor(mPointerColor);
        canvas.drawLine(getWidth()/2 - 20,getHeight()/2,getWidth()/2-5,getHeight()/2,mPaint);
        //绘制圆心
        canvas.drawCircle(getWidth()/2,getHeight()/2,5,mPaint);
        canvas.drawLine(getWidth()/2+5,getHeight()/2,getWidth()/2+mSecondPointerLength,getHeight()/2,mPaint);
        //绘制秒刻度
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(1);
        float startX,startY,endX,endY;
        float radius = mSecondPointerLength;
        double angle = Math.PI/30;
        for (int i = 0; i < 60; i++) {
            if (i<15){ //0°-90°
                mPaint.setColor(Color.WHITE);
                startX = (float) (radius*(Math.cos(i*angle)))+getWidth()/2;
                startY = (float) (radius*((Math.sin(i*angle))))+getHeight()/2;
                endX = (float) ((radius - mSecondScaleLength)*Math.cos(i*angle))+getWidth()/2;
                endY = (float) ((radius - mSecondScaleLength)*Math.sin(i*angle))+getHeight()/2;
                canvas.drawLine(startX,startY,endX,endY,mPaint);
                if (i%5 == 0){
                    mPaint.setTextSize(20);
                    canvas.drawText(String.valueOf(i+15),endX-30*(float)Math.cos(i*angle),endY-30*(float)Math.sin(i*angle),mPaint);
                }
                //在每两个秒刻度之间画5个小刻度
                for (int j = 1; j < 5; j++) {
                    mPaint.setColor(Color.GRAY);
                    startX = (float) (radius*(Math.cos(i*angle+j*angle/5)))+getWidth()/2;
                    startY = (float) (radius*((Math.sin(i*angle+j*angle/5))))+getHeight()/2;
                    endX = (float) ((radius - mChildSecondScaleLength)*Math.cos(i*angle+j*angle/5))+getWidth()/2;
                    endY = (float) ((radius - mChildSecondScaleLength)*Math.sin(i*angle+j*angle/5))+getHeight()/2;
                    canvas.drawLine(startX,startY,endX,endY,mPaint);
                }
            }
            else if(i>= 15 && i<30){//90°-180°
                mPaint.setColor(Color.WHITE);
                startX = (float) (getWidth()/2 - (radius*(Math.sin((i-15)*angle))));
                startY = (float) (radius*((Math.cos((i-15)*angle))))+getHeight()/2;
                endX = (float) (getWidth()/2 - (radius - mSecondScaleLength)*Math.sin((i-15)*angle));
                endY = (float) ((radius - mSecondScaleLength)*Math.cos((i-15)*angle))+getHeight()/2;
                canvas.drawLine(startX,startY,endX,endY,mPaint);
                if (i%5 == 0){
                    mPaint.setTextSize(20);
                    canvas.drawText(String.valueOf(i+15),endX+20*(float)Math.sin((i-15)*angle),endY-20*(float)Math.cos((i-15)*angle),mPaint);
                }
                //在每两个秒刻度之间画5个小刻度
                for (int j = 1; j < 5; j++){
                    mPaint.setColor(Color.GRAY);
                    startX = (float) (getWidth()/2 - (radius*(Math.sin((i-15)*angle+j*angle/5))));
                    startY = (float) (radius*((Math.cos((i-15)*angle+j*angle/5))))+getHeight()/2;
                    endX = (float) (getWidth()/2 - (radius - mChildSecondScaleLength)*Math.sin((i-15)*angle+j*angle/5));
                    endY = (float) ((radius - mChildSecondScaleLength)*Math.cos((i-15)*angle+j*angle/5))+getHeight()/2;
                    canvas.drawLine(startX,startY,endX,endY,mPaint);
                }
            }
            else if (i>=30 && i<45){//180°-270°
                mPaint.setColor(Color.WHITE);
                startX = (float) (getWidth()/2 -radius*(Math.cos((i-30)*angle)));
                startY = (float) (getHeight()/2 - radius*((Math.sin((i-30)*angle))));
                endX = (float) (getWidth()/2 -(radius - mSecondScaleLength)*Math.cos((i-30)*angle));
                endY = (float) (getHeight()/2 - (radius - mSecondScaleLength)*Math.sin((i-30)*angle));
                canvas.drawLine(startX,startY,endX,endY,mPaint);
                if (i%5 == 0){
                    mPaint.setTextSize(20);
                    canvas.drawText(String.valueOf(i+15),endX+20*(float)Math.cos((i-30)*angle),endY+20*(float)Math.sin((i-30)*angle),mPaint);
                }
                //在每两个秒刻度之间画5个小刻度
                for (int j = 1; j < 5; j++){
                    mPaint.setColor(Color.GRAY);
                    startX = (float) (getWidth()/2 -radius*(Math.cos((i-30)*angle+j*angle/5)));
                    startY = (float) (getHeight()/2 - radius*((Math.sin((i-30)*angle+j*angle/5))));
                    endX = (float) (getWidth()/2 -(radius - mChildSecondScaleLength)*Math.cos((i-30)*angle+j*angle/5));
                    endY = (float) (getHeight()/2 - (radius - mChildSecondScaleLength)*Math.sin((i-30)*angle+j*angle/5));
                    canvas.drawLine(startX,startY,endX,endY,mPaint);
                }
            }
            else{//270°-360°
                mPaint.setColor(Color.WHITE);
                startX = (float) (radius*(Math.sin((i-45)*angle)))+getWidth()/2;
                startY = (float) (getHeight()/2 - radius*((Math.cos((i-45)*angle))));
                endX = (float) ((radius - mSecondScaleLength)*Math.sin((i-45)*angle))+getWidth()/2;
                endY = (float) (getHeight()/2 - (radius - mSecondScaleLength)*Math.cos((i-45)*angle));
                canvas.drawLine(startX,startY,endX,endY,mPaint);
                if (i%5 == 0){
                    mPaint.setTextSize(20);
                    if (i==45){
                        canvas.drawText(String.valueOf(60),endX-40*(float)Math.sin((i-45)*angle) - 10,endY+30*(float)Math.cos((i-45)*angle),mPaint);
                    }else{
                        canvas.drawText(String.valueOf(i-45),endX-40*(float)Math.sin((i-45)*angle) - 10,endY+30*(float)Math.cos((i-45)*angle),mPaint);
                    }
                }
                //在每两个秒刻度之间画5个小刻度
                for (int j = 1; j < 5; j++){
                    mPaint.setColor(Color.GRAY);
                    startX = (float) (radius*(Math.sin((i-45)*angle+j*angle/5)))+getWidth()/2;
                    startY = (float) (getHeight()/2 - radius*((Math.cos((i-45)*angle+j*angle/5))));
                    endX = (float) ((radius - mChildSecondScaleLength)*Math.sin((i-45)*angle+j*angle/5))+getWidth()/2;
                    endY = (float) (getHeight()/2 - (radius - mChildSecondScaleLength)*Math.cos((i-45)*angle+j*angle/5));
                    canvas.drawLine(startX,startY,endX,endY,mPaint);
                }
            }
        }

    }
}
