package com.hechuangwu.paintdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hechuangwu.paintdemo.paint.WaveView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //        setContentView( new PaintView( this ) );//圆形，折线，文本
        //                setContentView( new GradientView( this ) );//圆形图片，矩形，椭圆，渐变
        //                setContentView( new ZoomImageView( this ) );//放大镜效果
//        setContentView( new PathMeasureView( this ) );//pathMeasure的使用

                WaveView waveView = new WaveView( this );//水波上涨效果
                setContentView(  waveView );
                waveView.startBoard();



//        setContentView( R.layout.activity_main );
    }

    public void reveal(View view) {
        startActivity( new Intent( this, RevealActivity.class ) );
    }

    public void search(View view) {
        startActivity( new Intent( this, SearchActivity.class ) );
    }

}
