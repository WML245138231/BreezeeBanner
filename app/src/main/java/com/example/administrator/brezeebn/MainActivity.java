package com.example.administrator.brezeebn;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.breezeebanner.BreezeeBanner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BreezeeBanner breezeeBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        breezeeBanner= (BreezeeBanner) findViewById(R.id.viewPager);
        ArrayList<Object> imageViews=new ArrayList<>();
        ImageView imageView1=new ImageView(this);
        imageView1.setImageResource(R.drawable.gggg);
        imageViews.add(0,imageView1);
        ImageView imageView2=new ImageView(this);
        imageView2.setImageResource(R.drawable.ssss);
        imageViews.add(1,imageView2);
        ImageView imageView3=new ImageView(this);
        imageView3.setImageResource(R.drawable.fffff);
        imageViews.add(2,imageView3);
        breezeeBanner.init(imageViews);
    }
}
