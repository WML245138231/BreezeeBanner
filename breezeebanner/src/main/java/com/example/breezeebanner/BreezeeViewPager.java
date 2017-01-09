package com.example.breezeebanner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/9.
 */

public class BreezeeViewPager extends ViewPager {

    private BreezeeAdapter adapter;
    private ArrayList<Object> views;
    private final static int AuthPlay = 0;
    private ArrayList<View> points;

    public BreezeeViewPager(Context context) {
        super(context);
    }

    public BreezeeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(ArrayList<Object> views, ArrayList<Object> paths, ArrayList<View> points) {
        this.views = new ArrayList<>();
        this.points = points;
        if (views.size() > 1) {
            ImageView imageViewHead = new ImageView(getContext());
            imageViewHead.setScaleType(ImageView.ScaleType.FIT_XY);
            this.views.add(0, imageViewHead);
            for (int i = 1; i < views.size() + 1; i++) {
                this.views.add(i, views.get(i - 1));
            }
            ImageView imageViewFoot = new ImageView(getContext());
            imageViewFoot.setScaleType(ImageView.ScaleType.FIT_XY);
            this.views.add(views.size() + 1, imageViewFoot);
        } else {
            this.views.addAll(views);
        }
        for (int i = 0; i < this.views.size(); i++) {
            if (i == 0) {
                getImge((ImageView) this.views.get(i), (String) paths.get(paths.size() - 1));
            } else if (i == this.views.size() - 1) {
                getImge((ImageView) this.views.get(i), (String) paths.get(0));
            } else {
                getImge((ImageView) this.views.get(i), (String) paths.get(i - 1));
            }
        }
        initView();
    }

    private void initView() {
        addOnPageChangeListener(onPageChangeListener);
        setOffscreenPageLimit(this.views.size());
        adapter = new BreezeeAdapter(this.views);
        this.setAdapter(adapter);
        setOverScrollMode(OVER_SCROLL_NEVER);
        if (views.size() > 1) {
            setCurrentItem(1, false);
        }
        startPlaying();
    }

    public synchronized void startPlaying() {
        handler.sendEmptyMessageDelayed(AuthPlay, 5000);
    }

    public void showNextView() {
        if (views == null || views.size() <= 1)
            return;
        setCurrentItem(BreezeeViewPager.this.getCurrentItem() + 1, true);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == AuthPlay) {
                handler.sendEmptyMessageDelayed(AuthPlay, 5000);
                showNextView();
            }
            return false;
        }
    });
    private int currentPosition;
    private boolean isChanging = false;
    ViewPager.OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPosition = position;
            for (View point : points) {
                point.setBackgroundResource(R.drawable.point_ffffff);
            }
            if (position <= points.size() && position > 0)
                points.get(position - 1).setBackgroundResource(R.drawable.point_red);
            if (position == 0)
                points.get(points.size() - 1).setBackgroundResource(R.drawable.point_red);
            else if (position + 1 == views.size())
                points.get(0).setBackgroundResource(R.drawable.point_red);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (views.size() > 1) {
                if (state == 1) {
                } else if (state == 0) {
                    currentPosition = BreezeeViewPager.this.getCurrentItem();
                    if (currentPosition + 1 == views.size()) {
                        isChanging = true;
                        setCurrentItem(1, false);
                    } else if (currentPosition == 0) {
                        isChanging = true;
                        setCurrentItem(views.size() - 2, false);
                    }
                } else if (state == 2) {

                }
            }
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isChanging) {
            if (BreezeeViewPager.this.getCurrentItem() != 0 || BreezeeViewPager.this.getCurrentItem() != views.size() - 1)
                isChanging = false;
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) {
            handler.removeMessages(AuthPlay);
            startPlaying();
        } else {
            handler.removeMessages(AuthPlay);
            if (isChanging) {
                if (BreezeeViewPager.this.getCurrentItem() != 0 || BreezeeViewPager.this.getCurrentItem() != views.size() - 1)
                    isChanging = false;
                return false;
            } else {
                return super.onTouchEvent(ev);
            }
        }
        return super.onTouchEvent(ev);
    }

    public void getImge(ImageView imageView, String path) {
        Glide.with(getContext())
                .load(path)
                .into(imageView);
    }
}
