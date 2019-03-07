package com.example.lenovo.imitatewechatnavigation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.lenovo.imitatewechatnavigation.R;

public class MyImageView extends ImageView {//代码还会运行，这只是一个警告

    private Paint mPaint;
    private Bitmap mSelectedIcon;
    private Bitmap mNormalIcon;
    private Rect mSelectedRect;
    private Rect mNormalRect;
    private int mSelectedAlpha=0;

    public MyImageView(Context context){
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public MyImageView(Context context, AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    public final void setImages(int normal,int selected){
        this.mNormalIcon =createBitmap(normal);//拿到原图
        this.mSelectedIcon=createBitmap(selected);//拿到“被选中选项”的图

        int width=(int)getResources().getDimension(R.dimen.tab_image_weith);
        int height=(int)getResources().getDimension(R.dimen.tab_image_heigh);

        this.mNormalRect=new Rect(0,0,width,height);//拿到画板的大小
        this.mSelectedRect=new Rect(0,0,width,height);

        this.mPaint=new Paint();//拿到画笔
    }

    private Bitmap createBitmap(int resId){
        return BitmapFactory.decodeResource(getResources(),resId);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(this.mPaint==null)
            return;
        this.mPaint.setAlpha(255-this.mSelectedAlpha);
        canvas.drawBitmap(this.mNormalIcon,null,this.mNormalRect,this.mPaint);
        //开始在画板上画原图

        this.mPaint.setAlpha(this.mSelectedAlpha);
        canvas.drawBitmap(this.mSelectedIcon,null,this.mSelectedRect,this.mPaint);
    }

    /*public final void changeSelectedAlpha(int alpha){

    }*/

    public final void transformPage(float offset){
        this.mSelectedAlpha=(int)(255*(1-offset));
        invalidate();//此方法调用就会重新走onDrew方法
    }
}






