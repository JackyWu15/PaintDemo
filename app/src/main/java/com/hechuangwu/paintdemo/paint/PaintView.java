package com.hechuangwu.paintdemo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cwh on 2019/7/8 0008.
 * 功能:
 */
public class PaintView extends View {


    private Paint mPaint;

    public PaintView(Context context) {
        this( context, null );
    }

    public PaintView(Context context, AttributeSet attrs) {
        this( context, attrs, 0 );
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );
        mPaint.reset();

        //画圆
        mPaint.setColor( Color.GREEN );
        mPaint.setAlpha( 127 );
        canvas.drawCircle( getMeasuredWidth() / 2, getMeasuredHeight() / 2, 50, mPaint );

        //画路径
        mPaint.setStyle( Paint.Style.FILL );//填充(默认)
        mPaint.setStyle( Paint.Style.STROKE );//描边
        //填充时无效，以下皆无效
        mPaint.setStrokeWidth( 50 );
        mPaint.setStrokeJoin( Paint.Join.MITER );//锐角(默认)
        mPaint.setStrokeJoin( Paint.Join.ROUND );//圆角
//        mPaint.setStrokeJoin( Paint.Join.BEVEL );//折角
        mPaint.setStrokeCap( Paint.Cap.BUTT );//没有
        mPaint.setStrokeCap( Paint.Cap.ROUND );//圆形
        mPaint.setStrokeCap( Paint.Cap.SQUARE );//方形

        //设置是否使用图像抖动处理。会使绘制的图片等颜色更加的清晰以及饱满。（损失性能）
        mPaint.setDither(true);
        Path path = new Path();
        path.moveTo( 100, 200 );
        path.lineTo( 300, 200 );
        path.lineTo( 100, 1000 );
        canvas.drawPath( path, mPaint );


        mPaint.setStrokeWidth( 1 );
        int top = 100;
        int baselineX = 0;
        mPaint.setTextSize(200);
        mPaint.setTextAlign(Paint.Align.LEFT);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, top, 2000, top, mPaint);

        mPaint.setColor(Color.RED);

        String str = "h哈哈哈";
        //文本Metrics
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//        		Paint.FontMetrics fontMetrics = mPaint.getFontMetricsInt();
//        		fontMetrics.top;
//        		fontMetrics.ascent;
//        		fontMetrics.descent;
//        		fontMetrics.bottom;

        float baselineY = top - fontMetrics.top;
        canvas.drawText(str, baselineX, baselineY, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawText(str, baselineX, top, mPaint);
//        mPaint.setColor(Color.YELLOW);
//        baselineY = top + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom;
//        canvas.drawText(str, baselineX, baselineY, mPaint);


    }
}
