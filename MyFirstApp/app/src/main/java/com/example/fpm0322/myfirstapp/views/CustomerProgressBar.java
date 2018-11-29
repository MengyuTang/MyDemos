package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.fpm0322.myfirstapp.R;

public class CustomerProgressBar extends View {

    /**
     * 第一圈的颜色
     */
    private int firstColor;

    /**
     * 第二圈的颜色
     */
    private int secondColor;

    /**
     * 旋转速度
     */
    private int speed;

    /**
     * 圆环宽度
     */
    private int circleWidth;

    /**
     * 当前进度
     */
    private int progress;

    private Paint mPaint;

    /**
     * 是否进入下一圈
     */
    private boolean isNext = false;

    public CustomerProgressBar(Context context) {
        this(context,null);
    }

    public CustomerProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomerProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomerProgressBar,defStyleAttr,0);
        int num = typedArray.getIndexCount();
        if (num>0){
            for (int i = 0; i < num; i++) {
                int attr = typedArray.getIndex(i);
                switch (attr){
                    case R.styleable.CustomerProgressBar_firstColor:
                        firstColor = typedArray.getColor(attr, Color.YELLOW);
                        break;

                    case R.styleable.CustomerProgressBar_secondColor:
                        secondColor = typedArray.getColor(attr, Color.GREEN);
                        break;

                    case R.styleable.CustomerProgressBar_speed:
                        speed = typedArray.getInt(attr,20);
                        break;

                    case R.styleable.CustomerProgressBar_circleWidth:
                        circleWidth = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,20,
                                getResources().getDisplayMetrics()));
                        break;
                }
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    progress++;
                    if (progress == 360){
                        progress = 0;
                        isNext = !isNext;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth()/2; //圆心
        int radius = center - circleWidth/2; //半径
        mPaint.setStrokeWidth(circleWidth); //圆环宽度
        mPaint.setAntiAlias(true); //消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); //空心
        RectF rectF = new RectF(center - radius,center - radius,center+radius,center+radius);
        if (!isNext){
            //先画第一圈，并用第二圈的颜色画弧度
            mPaint.setColor(firstColor);
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(secondColor);
            canvas.drawArc(rectF,-90,progress,false,mPaint);
        }else{
            mPaint.setColor(secondColor);
            canvas.drawCircle(center,center,radius,mPaint);
            mPaint.setColor(firstColor);
            canvas.drawArc(rectF,-90,progress,false,mPaint);
        }
    }
}
