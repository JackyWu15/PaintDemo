package com.hechuangwu.paintdemo.paint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hechuangwu.paintdemo.R;

/**
 * Created by cwh on 2019/7/30 0030.
 * 功能:
 */
public class WaveView extends View {
    private Paint paint;
    private Path path;
    private int waveLength = 800;
    private int dx;
    private int dy;
    private Bitmap mBitmap;
    private int width, height;
    private PathMeasure mPathMeasure;
    private float[] pos;
    private float[] tan;
    private Matrix mMatrix;
    protected float fraction;

    public WaveView(Context context) {
        super( context );
        initPaint();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super( context, attrs );
        initPaint();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor( Color.RED );
        paint.setStyle( Paint.Style.FILL_AND_STROKE );

        path = new Path();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        mBitmap = BitmapFactory.decodeResource( getResources(), R.drawable.timg, options );

        pos = new float[2];
        tan = new float[2];
        mMatrix = new Matrix();
    }


    private void drawWave() {
        path.reset();
        int originY = 1000;
        int halfWaveLength = waveLength / 2;
        if (dy < originY + 150) {
            dy += 2;
        }
        path.moveTo( -waveLength + dx, originY - dy );
        for (int i = -waveLength; i < width + waveLength; i += waveLength) {
            //相对绘制二阶贝塞尔曲线(相对于自己的起始点--也即是上一个曲线的终点  的距离dx1)
            path.rQuadTo( halfWaveLength / 2, -150, halfWaveLength, 0 );
            path.rQuadTo( halfWaveLength / 2, 150, halfWaveLength, 0 );

        }
        path.lineTo( width, height );
        path.lineTo( 0, height );
        path.close();
        mPathMeasure = new PathMeasure( this.path, false );


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged( w, h, oldw, oldh );
        width = w;
        height = h;
        drawWave();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );
        drawWave();
        float length = 2 * waveLength + width;

        mMatrix.reset();
        //指定相对起始位置哪个点，设置Matrix
        mPathMeasure.getMatrix( length * fraction, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG );//flag:表示要求哪一个值：tan或者pos
        //让图片位于水波上
        mMatrix.preTranslate( 0, -mBitmap.getHeight() );

        canvas.drawBitmap( mBitmap, mMatrix, paint );
        canvas.drawPath( path, paint );
    }

    public void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt( 0, waveLength );
        animator.setDuration( 1000 );
        animator.setInterpolator( new LinearInterpolator() );
        animator.setRepeatCount( ValueAnimator.INFINITE );
        animator.addUpdateListener( new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        } );
        animator.start();
    }

    public void startBoard() {
        ValueAnimator animator = ValueAnimator.ofInt( 0, waveLength );
        animator.setDuration(2000 );
        animator.setInterpolator( new LinearInterpolator() );
        animator.setRepeatCount( ValueAnimator.INFINITE );
        animator.addUpdateListener( new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        } );
        animator.start();


        ValueAnimator animatorBoard = ValueAnimator.ofFloat( 0, 1 );
        animatorBoard.setDuration( 2000 );
        animatorBoard.setInterpolator( new LinearInterpolator() );
        animatorBoard.setRepeatCount( ValueAnimator.INFINITE );
        animatorBoard.addUpdateListener( new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        } );
        animatorBoard.start();

    }
}
