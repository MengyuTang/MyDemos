package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BezierCurve extends View{
    Paint p = new Paint();
    Path path = new Path();
    public BezierCurve(Context context) {
        this(context,null);
    }

    public BezierCurve(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierCurve(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        path.moveTo(200,200);
        path.quadTo(mSuperX,mSuperY,400,200);
        canvas.drawPath(path,p);
        super.onDraw(canvas);
    }

    float mSuperX;

    float mSuperY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mSuperX = event.getX();
                mSuperY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
