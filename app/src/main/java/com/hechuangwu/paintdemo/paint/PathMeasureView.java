package com.hechuangwu.paintdemo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;

/**
 * Created by cwh on 2019/7/30 0030.
 * 功能:
 */
public class PathMeasureView extends View {
    //最好关闭硬件加速器
    private Paint paint;
    private int mViewHeight, mViewWidth;

    public PathMeasureView(Context context) {
        super( context );
        initPaint();

    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor( Color.RED );
        paint.setStyle( Paint.Style.STROKE );
        paint.setStrokeWidth( 10 );

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged( w, h, oldw, oldh );
        mViewHeight = h;
        mViewWidth = w;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );
        canvas.translate( mViewWidth / 2, mViewHeight / 2 );
        //----------------------------是否加上闭合长度--------------------------
        //
        //        Path path = new Path();
        //        path.lineTo( 0,200 );
        //        path.lineTo( 200,200 );
        //        path.lineTo( 200,0 );
        //
        //        PathMeasure pathMeasure1 = new PathMeasure( path, false );//600
        //        PathMeasure pathMeasure2 = new PathMeasure( path, true );//800算上关闭


        //----------------------------添加多个path--------------------------
        //        Path path = new Path();
        //        path.addRect( -200, -200, 200, 200, Path.Direction.CW );
        //        Path path1 = new Path();
        //        path1.addRect( -100, -100, 100, 100, Path.Direction.CW );
        //        path.addPath( path1 );
        //        PathMeasure pathMeasure = new PathMeasure( path, false );
        //        float length = pathMeasure.getLength();
        //        boolean b = pathMeasure.nextContour();
        //        if (b) {
        //            float length1 = pathMeasure.getLength();
        //        }
        //----------------------------截取--------------------------

//        Path path = new Path();
//        path.addRect( -200, -200, 200, 200, Path.Direction.CW );
//        PathMeasure measure = new PathMeasure( path, false );
//        canvas.drawPath( path, paint );
//
//        Path dst = new Path();
//        dst.lineTo( -300, -300 );
//        //startWithMoveTo:false: true起点为startD，false起点为dst开始点
//        measure.getSegment( 200,600,dst,true);
//        paint.setColor(Color.GREEN);


        //-----------------------------角度----------------------------
        Path path = new Path();
        path.addCircle( 0,0,300,Path.Direction.CW );
        PathMeasure pathMeasure = new PathMeasure( path, false );
        float[] pos = new float[2];
        float[] tan = new float[2];//tan=y/x
        pathMeasure.getPosTan(pathMeasure.getLength()/4,pos,tan);
        Log.i( "data", "onDraw: position:x="+pos[0]+", y="+pos[1] );
        Log.i( "data", "onDraw: tan:x="+tan[0]+", y="+tan[1] );

        canvas.drawPath(path, paint);
    }
}
