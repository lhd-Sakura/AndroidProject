package com.example.airplane;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.airplane.R;

import java.lang.reflect.Field;

public class MainActivity extends Activity implements View.OnTouchListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private int startX = 0;
    private int startY = 0;
    private ImageView mImageView;
    private int mTop;
    private int mLeft;
    private int mMeasuredWidth;
    private int mMeasuredHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.plane);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mImageView.measure(w, h);//测量控件的宽高
        mMeasuredWidth = mImageView.getMeasuredWidth();
        mMeasuredHeight = mImageView.getMeasuredHeight();
        mImageView.setOnTouchListener(this);
        mTop = getScreenHeight() - dip2px(100);
        mLeft = (getScreenWidth() / 2) - (mMeasuredWidth / 2);
        setImageViewMargin(0, 0);  //初始化控件据底部100dp 居中显示
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //初始化开始位置
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                mTop = mImageView.getTop();
                mLeft = mImageView.getLeft();
                break;
            case MotionEvent.ACTION_MOVE:
                //手势移动的dX和dY为控件的marginLeft和marginTop
                int moveX = (int) event.getRawX();
                int moveY = (int) event.getRawY();
                int dX = moveX - startX;
                int dY = moveY - startY;
                setImageViewMargin(dX, dY);
                break;
            case MotionEvent.ACTION_UP:
                //初始值重置
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                mTop = mImageView.getTop();
                mLeft = mImageView.getLeft();
                break;
        }
        return true;
    }

    /**
     * 动态设置控件的marginTop 和 marginLeft的值
     *
     * @param dX x轴的偏移量
     * @param dY y轴的偏移量
     */
    private void setImageViewMargin(int dX, int dY) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
        int top = mTop + dY;
        int left = mLeft + dX;
        int l = getScreenWidth() - mMeasuredWidth;
        int t = getScreenHeight() - mMeasuredHeight - getStatusBarHeight();
        //设置left和top的边界值
        if (left < 0) {
            left = 0;
        } else if (left > l) {
            left = l;
        }
        if (top < 0) {
            top = 0;
        } else if (top > t) {
            top = t;
        }
        layoutParams.topMargin = top;
        layoutParams.leftMargin = left;
        mImageView.setLayoutParams(layoutParams);
    }


    /**
     * 获取状态栏的高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        return defaultDisplay.getWidth();
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    private int getScreenHeight() {
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        return defaultDisplay.getHeight();
    }


    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    private int dip2px(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        return (int) (dp * density + 0.5);
    }


    /**
     * px转dp
     *
     * @param px
     * @return
     */
    private int px2dip(int px) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }


}


