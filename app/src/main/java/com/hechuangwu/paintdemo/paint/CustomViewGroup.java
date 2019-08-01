package com.hechuangwu.paintdemo.paint;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwh on 2019/8/1 0001.
 * 功能:
 */
public class CustomViewGroup extends ViewGroup {
    List<Integer> lineHeights = new ArrayList<Integer>();//存每一行最大高度
    List<List<View>> views = new ArrayList<List<View>>();//存所有子控件
    public CustomViewGroup(Context context) {
        super( context );
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super( context, attrs );
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
    }

    //父控件会调用此方法来测量CustomView,记录测量数据是为了onLayout时使用
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
        //测量自身
        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量子控件，如果不进行测量，父控件不知道子控件尺寸，不会显示
        int lineWidth = 0;
        int lineHeight = 0;
        int maxLineWidth = 0;
        int sumHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt( i );
            //父控件本身被限制的MeasureSpec,和子控件本身设置的宽高属性，共同决定子控件的MeasureSpec
            //            LayoutParams layoutParams = childAt.getLayoutParams();
            //            int childWidthMeasureSpec = getChildMeasureSpec( widthMeasureSpec, 0, layoutParams.width );
            //            int childHeightMeasureSpec = getChildMeasureSpec( heightMeasureSpec, 0, layoutParams.height );
            //            //共同确定完成，控件开始测量本身
            //            childAt.measure( childWidthMeasureSpec,childHeightMeasureSpec );

            measureChild( childAt,widthMeasureSpec,heightMeasureSpec );
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childWidth  = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + childAt.getMeasuredWidth();
            int childHeight = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + childAt.getMeasuredHeight();

            //需要换行
            if(lineWidth+childWidth>widthSize){
                //记录最长的一行
                maxLineWidth = Math.max( lineWidth,maxLineWidth );
                //当前子控件放置到下一行
                lineWidth = childWidth;

                sumHeight += lineHeight;
                lineHeight = childHeight;
                //同一行
            }else {
                lineWidth +=childWidth;
                lineHeight = Math.max( lineHeight,childHeight );
            }

            //最后一行
            if(i==childCount-1){
                maxLineWidth = Math.max( lineWidth,maxLineWidth );
                sumHeight +=lineHeight;
            }
        }

        //测量自身并定义大小
        int measureWidth = (widthMode == MeasureSpec.EXACTLY) ? widthSize : maxLineWidth;
        int measuredHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize : sumHeight;
        setMeasuredDimension( measureWidth,measuredHeight );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        views.clear();
        lineHeights.clear();
        //一行有多少数据
        List<View> lineViews = new ArrayList<>();
        int measuredWidth = getMeasuredWidth();
        int childCount = getChildCount();
        int lineWidth = 0;//行宽
        int maxLineHeight = 0;//一行里最大高度
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt( i );
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childWidth = childAt.getMeasuredWidth();
            int childHeight = childAt.getMeasuredHeight();

            if(childWidth+marginLayoutParams.leftMargin+marginLayoutParams.rightMargin+lineWidth>measuredWidth){
                lineHeights.add(maxLineHeight );
                views.add( lineViews );
                lineWidth = 0;
                lineViews = new ArrayList<>(  );

            }
            lineWidth +=childWidth+marginLayoutParams.leftMargin+marginLayoutParams.rightMargin;
            maxLineHeight = Math.max( maxLineHeight,childHeight+marginLayoutParams.topMargin+marginLayoutParams.bottomMargin );
            lineViews.add( childAt );
        }
        lineHeights.add(maxLineHeight );
        views.add( lineViews );


        //开始摆放
        int left = 0;
        int top = 0;
        int size = views.size();
        for (int i = 0; i < size; i++) {
            lineViews = views.get( i );
            Integer lineHeight = lineHeights.get( i );
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get( j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
                int v_left = left+layoutParams.leftMargin;
                int v_top = top+layoutParams.topMargin;
                int v_right = v_left+view.getMeasuredWidth();
                int v_bottom = v_top+view.getMeasuredHeight();
                view.layout( v_left,v_top,v_right,v_bottom );
                left +=view.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            }
            left = 0;
            top +=lineHeight;

        }

    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        // TODO Auto-generated method stub
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        // TODO Auto-generated method stub
        return new MarginLayoutParams(getContext(), attrs);
    }
}
