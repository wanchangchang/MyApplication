/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.commenttool.R;


public class RefreshAnimView extends View {

    private Bitmap daishu;
    private int measuredWidth;
    private int measuredHeight;
    private float mCurrentProgress;
    private int mCurrentAlpha;
    private Paint mPaint;
    private Bitmap scaleDaishu;

    public RefreshAnimView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RefreshAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshAnimView(Context context) {
        super(context);
        init();
    }

    private void init() {
        // Login bitmap
        daishu = BitmapFactory.decodeResource(getResources(), R.drawable.terry_pao_bu_one);
        // mPaint.setAlpha来实现渐变效果
        mPaint = new Paint();
        // 首先设置为完全透明
        mPaint.setAlpha(0);
    }

    /**
     * 测量方法
     * 
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    // 测量宽度
    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (MeasureSpec.EXACTLY == mode) {
            result = size;
        }
        else {
            result = daishu.getWidth();
            if (MeasureSpec.AT_MOST == mode) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    // 测量高度
    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (MeasureSpec.EXACTLY == mode) {
            result = size;
        }
        else {
            result = daishu.getHeight();
            if (MeasureSpec.AT_MOST == mode) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    // 在这里面拿到测量后的宽和高，w就是测量后的宽，h是测量后的高
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        try {
            measuredWidth = w;
            measuredHeight = h;
            if(w<0||h<0){
                return;
            }
            // 根据测量后的宽高来对Login做一个缩放
            scaleDaishu = Bitmap.createScaledBitmap(daishu, measuredWidth, measuredHeight, true);
        }
        catch (Exception e) {

        }

    }

    /**
     * 绘制方法
     * 
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.scale(mCurrentProgress, mCurrentProgress, measuredWidth / 2, measuredHeight);
        mPaint.setAlpha(mCurrentAlpha);
        canvas.drawBitmap(scaleDaishu, 0, 0, mPaint);
    }

    /**
     * 根据进度对Login进行缩放
     * 
     * @param currentProgress
     */
    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        this.mCurrentAlpha = (int) (currentProgress * 255);
    }
}
