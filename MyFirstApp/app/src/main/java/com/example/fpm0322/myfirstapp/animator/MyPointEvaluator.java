package com.example.fpm0322.myfirstapp.animator;

import android.animation.TypeEvaluator;

public class MyPointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object point, Object t1) {
        Point startValue = (Point)point;
        Point endValue = (Point)t1;

        float x = startValue.getX()+v*(endValue.getX()-startValue.getX());
        float y = startValue.getY()+v*(endValue.getY()-startValue.getY());
        Point newPoint = new Point(x,y);
        return newPoint;
    }
}
