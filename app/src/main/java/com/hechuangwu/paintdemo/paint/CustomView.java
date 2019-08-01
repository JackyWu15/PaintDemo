package com.hechuangwu.paintdemo.paint;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cwh on 2017/8/1 0001.
 * 功能:
 */
public class CustomView extends ViewGroup {
    private static final int OFFSET = 80;

    public CustomView(Context context) {
        super( context );
    }

    public CustomView(Context context, AttributeSet attrs) {
        super( context, attrs );
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
    }

    //父控件会调用此方法来测量CustomView,记录测量数据是为了onLayout时使用
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量自身
        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量子控件，如果不进行测量，父控件不知道子控件尺寸，不会显示
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt( i );
            //父控件本身被限制的MeasureSpec,和子控件本身设置的宽高属性，共同决定子控件的MeasureSpec
            LayoutParams layoutParams = childAt.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec( widthMeasureSpec, 0, layoutParams.width );
            int childHeightMeasureSpec = getChildMeasureSpec( heightMeasureSpec, 0, layoutParams.height );
            //共同确定完成，控件开始测量本身
            childAt.measure( childWidthMeasureSpec,childHeightMeasureSpec );

        }


        //这里不使用系统super.onMeasure( widthMeasureSpec, heightMeasureSpec )测量，重写测量自身
//        int width = 0;
//        int height = 0;
//        switch (widthMode) {
//            case MeasureSpec.EXACTLY:
//                width = widthSize;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                for (int i = 0; i < childCount; i++) {
//                    View child = getChildAt(i);
//                    int widthAndOffset = i*OFFSET + child.getMeasuredWidth();
//                    width = Math.max(width, widthAndOffset);
//                }
//                break;
//            default:
//                break;
//        }
//
//
//        switch (heightMode) {
//            case MeasureSpec.EXACTLY:
//                height = heightSize;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                for (int i = 0; i < childCount; i++) {
//                    View child = getChildAt(i);
//                    height += child.getMeasuredHeight();
//                }
//                break;
//            default:
//                break;
//        }
//        //自身尺寸测量完成
//        //				ViewGroup.setMeasuredDimension(size);
//        setMeasuredDimension(width,height);
    }


    //摆放子控件的位置，不摆放，父控件不知道显示在哪，不会显示
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
       int left = 0;
       int top = 0;
       int right = 0;
       int bottom = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt( i );
            left = i*OFFSET;
            right = left+childAt.getMeasuredWidth();
            bottom =top +childAt.getMeasuredHeight();
            childAt.layout( left,top,right,bottom );
            top += childAt.getMeasuredHeight();
        }



    }


}
