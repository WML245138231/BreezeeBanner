package com.example.breezeebanner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/9.
 */

public class BreezeeBanner extends ViewPager {

    private BreezeeAdapter adapter;
    private ArrayList<Object> views;
    private final static int AuthPlay = 0;


    public BreezeeBanner(Context context) {
        super(context);
    }

    public BreezeeBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(ArrayList<Object> views) {
        this.views = new ArrayList<>();
        if (views.size() > 1) {
            ImageView imageViewHead = new ImageView(getContext());
            imageViewHead.setImageDrawable(((ImageView) views.get(views.size() - 1)).getDrawable());
            this.views.add(0, imageViewHead);
            for (int i = 1; i < views.size() + 1; i++) {
                this.views.add(i, views.get(i - 1));
            }
            ImageView imageViewFoot = new ImageView(getContext());
            imageViewFoot.setImageDrawable(((ImageView) views.get(0)).getDrawable());
            this.views.add(views.size() + 1, imageViewFoot);
        } else {
            this.views.addAll(views);
        }
        initView();
    }

    public void initView() {
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
        setCurrentItem(BreezeeBanner.this.getCurrentItem() + 1, true);
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
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (views.size() > 1)
                if (state == 1) {
                } else if (state == 0) {
                    currentPosition = BreezeeBanner.this.getCurrentItem();
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
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isChanging) {
            if (BreezeeBanner.this.getCurrentItem() != 0 || BreezeeBanner.this.getCurrentItem() != views.size() - 1)
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
            handler.sendEmptyMessageDelayed(AuthPlay, 5000);
        } else {
            handler.removeMessages(AuthPlay);
            if (isChanging) {
                if (BreezeeBanner.this.getCurrentItem() != 0 || BreezeeBanner.this.getCurrentItem() != views.size() - 1)
                    isChanging = false;
                return false;
            } else {
                return super.onTouchEvent(ev);
            }
        }
        return super.onTouchEvent(ev);
    }
}
