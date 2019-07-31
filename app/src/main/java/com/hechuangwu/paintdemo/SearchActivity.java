package com.hechuangwu.paintdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hechuangwu.paintdemo.search.Controller;
import com.hechuangwu.paintdemo.search.MySearchView;

public class SearchActivity extends AppCompatActivity {
    private MySearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        searchView = (MySearchView)findViewById(R.id.sv);
        searchView.setController(new Controller());
    }

    public void start(View v){
        searchView.startAnimation();
    }
    public void reset(View v){
        searchView.resetAnimation();
    }
}
