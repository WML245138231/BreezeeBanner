package com.example.breezeebanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Administrator on 2017/1/9.
 */

public class BreezeeBanner extends RelativeLayout {
    public BreezeeBanner(Context context) {
        super(context);
    }

    public BreezeeBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BreezeeBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private BreezeeViewPager viewPager;
    private final static
    @android.support.annotation.IdRes
    int viewPagerId = 0x951000;
    private ArrayList<Object> views;
    private ArrayList<View> points;

    private void initView() {
        viewPager = new BreezeeViewPager(getContext());
        viewPager.setId(viewPagerId);
        RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewPager.setLayoutParams(params);
        addView(viewPager);
        viewPager.requestLayout();
    }

    public void init(ArrayList<Object> imgPaths) {
        initView();
        views = new ArrayList<>();
        for (int i = 0; i < imgPaths.size(); i++) {
            views.add(initImagView());
        }
        points = initPoint(views.size());
        Collections.reverse(points);
        viewPager.init(views, imgPaths, points);
    }

    private ImageView initImagView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    private ArrayList<View> initPoint(int num) {
        ArrayList<View> points = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            View view = new View(getContext());
            view.setId(viewPagerId + i + 1);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.width = DensityUtil.dip2px(getContext(), 5);
            params.height = DensityUtil.dip2px(getContext(), 5);
            if (i == 0) {
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else{
                params.addRule(RelativeLayout.LEFT_OF,viewPagerId+i);
            }
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.bottomMargin = DensityUtil.dip2px(getContext(), 5);
            params.rightMargin = DensityUtil.dip2px(getContext(), 15);
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.point_ffffff);
            if (i==num-1)
                view.setBackgroundResource(R.drawable.point_red);
            points.add(view);
            addView(view);
        }
        return points;
    }
}
