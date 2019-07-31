package com.hechuangwu.paintdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hechuangwu.paintdemo.reveal.GallaryHorizonalScrollView;
import com.hechuangwu.paintdemo.reveal.RevealDrawable;

public class RevealActivity extends AppCompatActivity {

    private ImageView iv;
    private int[] mImgIds = new int[] { //7个
            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline,

            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline
    };
    private int[] mImgIds_active = new int[] {
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active,
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active
    };

    public Drawable[] revealDrawables;
    protected int level = 5000;
    private GallaryHorizonalScrollView hzv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        initData();
        initView();

        //		iv = (ImageView)findViewById(R.id.iv);
        //		iv.setImageDrawable(revealDrawables[0]);
        //		iv.setImageLevel(level);
        //		iv.setOnClickListener(new OnClickListener() {
        //			@Override
        //			public void onClick(View v) {
        //				level -= 1000;
        //				iv.getDrawable().setLevel(level );
        //			}
        //		});

    }


    private void initData(){
        revealDrawables = new Drawable[mImgIds.length];
    }

    private void initView()
    {
        for (int i = 0; i < mImgIds.length; i++)
        {
            RevealDrawable rd = new RevealDrawable(
                    getResources().getDrawable(mImgIds[i]),
                    getResources().getDrawable(mImgIds_active[i]),
                    RevealDrawable.HORIZONTAL);
            revealDrawables[i] = rd;
        }
        hzv = (GallaryHorizonalScrollView)findViewById(R.id.hsv);
        hzv.addImageViews(revealDrawables);
    }

}
