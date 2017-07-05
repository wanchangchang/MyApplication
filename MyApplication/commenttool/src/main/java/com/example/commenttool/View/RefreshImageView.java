/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

/**
 * copywrite 2015-2020 金地物业
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * RefreshImageView.java
 * 2016年4月25日 下午2:36:13
 * taojinsha
 */
package com.example.commenttool.View;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.commenttool.R;


public class RefreshImageView extends ImageView{
    private AnimationDrawable mAnimationDrawable;
    public RefreshImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RefreshImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshImageView(Context context) {
        super(context);
        init();
    }
    
    private void init(){
        mAnimationDrawable = new AnimationDrawable();
        mAnimationDrawable.setOneShot(false);
        mAnimationDrawable.addFrame(this.getResources().getDrawable(R.drawable.terry_pao_bu_one), 100);
        mAnimationDrawable.addFrame(this.getResources().getDrawable(R.drawable.terry_pao_bu_there), 100);
        mAnimationDrawable.addFrame(this.getResources().getDrawable(R.drawable.terry_pao_bu_four), 100);
        mAnimationDrawable.addFrame(this.getResources().getDrawable(R.drawable.terry_pao_bu_tow), 100);
        this.setBackgroundDrawable(mAnimationDrawable);
    }
    private void startAnimation(){
        if(mAnimationDrawable!=null && !mAnimationDrawable.isRunning()){
            mAnimationDrawable.start();
        }
    }
    
    private void stopAnimation(){
        if(mAnimationDrawable!=null && mAnimationDrawable.isRunning()){
            mAnimationDrawable.stop();
        }
    }
    
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility==View.VISIBLE){
            this.clearAnimation();
            System.gc();
            startAnimation() ;
        }else if(visibility==View.GONE){
            stopAnimation() ;
            this.clearAnimation();
        }
    }
}
