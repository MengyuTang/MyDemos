package com.example.fpm0322.myfirstapp.views;

import android.annotation.SuppressLint;
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

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CustomTitleView extends View {

    /**
     * 文本内容
     */
    private String mTitleText;

    /**
     * 文本颜色
     */
    private int mTitlteTextColor;

    /**
     * 字体大小
     */
    private int mTitleTextSize;

    /**
     * 绘制边界
     */
    private Rect mBound;

    /**
     * 画笔
     */
    private Paint mPaint;

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTitleView(Context context) {
        this(context,null);
    }

    /**
     * 获得自定义样式属性
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        if (n<0) return;
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch(attr){
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = typedArray.getString(attr);
                    break;

                case R.styleable.CustomTitleView_titleTextColor:
                    mTitlteTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;

                case R.styleable.CustomTitleView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                    default:
                        break;
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = randomText();
                postInvalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY){
            //一般是设置了明确的值或者是MATCH_PARENT
            width = widthSize;
        }else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textWidth = mBound.width();
            width = (int) (getPaddingLeft()+textWidth+getPaddingRight());
        }
        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textHeight = mBound.height();
            height = (int) (getPaddingTop()+textHeight+getPaddingBottom());
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(mTitlteTextColor);
        canvas.drawText(mTitleText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
    }

    private String randomText(){
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size()<4){
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i:set) {
            sb.append("").append(i);
        }
        return sb.toString();
    }

}
