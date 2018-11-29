package com.example.fpm0322.myfirstapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.fpm0322.myfirstapp.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CustomImageView extends View {

    /**
     * 文本内容
     */
    private String mTitle;

    /**
     * 文本颜色
     */
    private int mTitlteColor;

    /**
     * 字体大小
     */
    private int mTitleSize;
    /**
     * 图片
     */
    private Bitmap mImage;
    /**
     * 图片伸缩类型
     */
    private int mImageScaleType;

    /**
     * 绘制边界
     */
    private Rect mBound;

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 边框
     */
    private Rect mRect;

    private int mWidth;

    private int mHeight;

    private final int IMAGE_SCALE_FITXY = 0;
    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomImageView(Context context) {
        this(context,null);
    }

    /**
     * 获得自定义样式属性
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        if (n<0) return;
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch(attr){
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(attr,0));
                    break;

                case R.styleable.CustomImageView_imageScaleType:
                    mImageScaleType = typedArray.getInt(attr,0);
                    break;

                case R.styleable.CustomImageView_titleText:
                    mTitle = typedArray.getString(attr);
                    break;

                case R.styleable.CustomImageView_titleTextColor:
                    mTitlteColor = typedArray.getColor(attr, Color.BLACK);
                    break;

                case R.styleable.CustomImageView_titleTextSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleSize = typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                    default:
                        break;
            }
        }
        typedArray.recycle();
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitle,0, mTitle.length(),mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            //一般是设置了明确的值或者是MATCH_PARENT
            mWidth = widthSize;
        }else{
            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitle,0, mTitle.length(),mBound);
            float textWidth = mBound.width();
            int desireByTitle = (int) (getPaddingLeft()+textWidth+getPaddingRight());
            int desireByImage =  (getPaddingLeft()+mImage.getWidth()+getPaddingRight());
            if (widthMode == MeasureSpec.AT_MOST){
                int desire = Math.max(desireByImage, desireByTitle);
                mWidth = Math.min(desire,widthSize);
            }
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else{
            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitle,0, mTitle.length(),mBound);
            float textHeight = mBound.height();
            int desire = (int) (getPaddingTop()+mImage.getHeight()+textHeight+getPaddingBottom());
            if (heightMode == MeasureSpec.AT_MOST){
                mHeight = Math.min(desire,heightSize);
            }
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置边框
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();
        mPaint.setColor(mTitlteColor);
        mPaint.setStyle(Paint.Style.FILL);

        if (mBound.width()>mWidth){
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitle,paint,mWidth - getPaddingLeft()-getPaddingRight(),TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),mHeight - getPaddingBottom(),mPaint);
        }else {
            canvas.drawText(mTitle,mWidth/2-mBound.width()/2,mHeight/2+ mBound.height()/2,mPaint);
        }

        mRect.bottom -= mBound.height();
        if (mImageScaleType == IMAGE_SCALE_FITXY){
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }else{
            mRect.left = mWidth/2 - mImage.getWidth()/2;
            mRect.right = mWidth/2+mImage.getWidth()/2;
            mRect.top = (mHeight - mBound.height())/2 - mImage.getHeight()/2;
            mRect.bottom = (mHeight - mBound.height())/2 +mImage.getHeight()/2;
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }
    }

}
