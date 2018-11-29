package com.example.fpm0322.myfirstapp.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyPoint extends View {

    private Point currentPoint;
    private Paint paint;
    public static final float RADIUS = 40f;

    public MyPoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null){
            currentPoint = new Point(RADIUS,RADIUS);
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x,y,RADIUS,paint);

            Point startPoint = new Point(RADIUS,RADIUS);
            Point endPoint = new Point(700 ,1000);

            ValueAnimator animatorOfObject = ValueAnimator.ofObject(new MyPointEvaluator(),startPoint,endPoint);
            animatorOfObject.setDuration(2000);
            animatorOfObject.setRepeatCount(2);
            animatorOfObject.setRepeatMode(ValueAnimator.REVERSE);
            animatorOfObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    currentPoint = (Point) valueAnimator.getAnimatedValue();
                    invalidate();
                }
            });
            animatorOfObject.start();
        }else{
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, paint);
        }
    }
}
