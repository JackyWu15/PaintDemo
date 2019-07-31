package com.hechuangwu.paintdemo.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.hechuangwu.paintdemo.R;

public class GradientView extends View {

    private Bitmap bitmap;
    private Paint paint;
    private BitmapShader bitmapShader;
    private int width;
    private int height;
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.GRAY};
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private ComposeShader composeShader;

    public GradientView(Context context) {
        super( context );
        //		bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        bitmap = ((BitmapDrawable) getResources().getDrawable( R.drawable.avatar3 )).getBitmap();
        paint = new Paint();
        width = bitmap.getWidth();
        height = bitmap.getHeight();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );
        canvas.drawColor( Color.WHITE );

        //				canvas.drawBitmap(bitmap, 0, 0, paint);

        //TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
        //TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
        //TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）

        //				bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.MIRROR);
        bitmapShader = new BitmapShader( bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP );
        //		bitmapShader = new BitmapShader(bitmap, TileMode.REPEAT, TileMode.REPEAT);
                paint.setShader( bitmapShader );
                paint.setAntiAlias( true );

        //图片等比放大到宽/或高的大小，避免填充不足
        float scale = Math.max( width, height ) * 1f / Math.min( width, height );
        Matrix matrix = new Matrix();
        matrix.setScale( scale, scale );//缩放比例
        bitmapShader.setLocalMatrix( matrix );


        //画矩形
//        				canvas.drawRect(new Rect(100, 100, 800, 800), paint);
        //普通画圆会填充不满
//        				canvas.drawCircle(height/2, height/2, height/2, paint);
        //按比例画圆
        				canvas.drawCircle(Math.max(width, height)/2f, scale*Math.max(width, height)/2f, Math.max(width, height)/2f, paint);
        //矩形做椭圆内切
//                canvas.drawOval( new RectF( 0, 0, width, height ), paint );
        //也可画圆
        //				canvas.drawOval(new RectF(0, 0, width, width), paint);

        //通过shapeDrawable也可以实现
//                		ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//                		shapeDrawable.getPaint().setShader(bitmapShader);
//                		shapeDrawable.setBounds(0, 0, width, width);
//                		shapeDrawable.draw(canvas);


        //线性渐变
        //x0, y0, 起始点
        // x1, y1, 结束点
        //int[]  colors, 中间依次要出现的几个颜色
        //float[] positions,数组大小跟colors数组一样大，中间依次摆放的几个颜色分别放置在那个位置上(参考比例从左往右)
        //   tile
        //
        //        		LinearGradient linearGradient = new LinearGradient(0, 0, 400, 400, colors, null, Shader.TileMode.CLAMP);
        //        LinearGradient linearGradient = new LinearGradient( 0, 0, 400, 400, colors, null, Shader.TileMode.REPEAT );
        //        		paint.setShader(linearGradient);
        //        		canvas.drawRect(0, 0, 400, 400, paint);
        //      圆环嵌套效果
        //        		radialGradient = new RadialGradient(300, 300, 100, colors, null, Shader.TileMode.REPEAT);
        //        		paint.setShader(radialGradient);
        //        		canvas.drawCircle(300, 300, 300, paint);

        //梯度效果
        //        		sweepGradient = new SweepGradient(300, 300, colors, null);
        //        		paint.setShader(sweepGradient);
        //        		canvas.drawCircle(300, 300, 300, paint);

        //混合模式
        //        composeShader = new ComposeShader( linearGradient, bitmapShader, PorterDuff.Mode.SRC_OVER );
        //        paint.setShader( composeShader );
        //        canvas.drawRect( 200, 200, 800, 800, paint );
    }

}
