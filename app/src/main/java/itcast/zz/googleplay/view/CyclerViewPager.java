package itcast.zz.googleplay.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangx on 2016/8/1.
 */
public class CyclerViewPager extends ViewPager {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentItem = getCurrentItem();
            currentItem++;
            setCurrentItem(currentItem);
            handler.sendEmptyMessageDelayed(100, 4000);
        }
    };

    public CyclerViewPager(Context context) {
        super(context);
    }

    public CyclerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener listener) {
        InnerPagerChangeListener innerPagerChangeListener = new InnerPagerChangeListener(listener);

        super.addOnPageChangeListener(innerPagerChangeListener);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        // 装饰
        InnerPagerAdapter innerPagerAdapter = new InnerPagerAdapter(adapter);
        addOnPageChangeListener(null);
        super.setAdapter(innerPagerAdapter);
        this.setCurrentItem(1);

        //自动轮播
        startScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopScroll();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startScroll();
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void startScroll() {
        //启动轮播
        handler.sendEmptyMessageDelayed(100, 4000);
    }

    private void stopScroll(){
        handler.removeMessages(100);
    }

    class InnerPagerChangeListener implements OnPageChangeListener {

        private int position;
        private OnPageChangeListener listener;

        public InnerPagerChangeListener(OnPageChangeListener listener) {

            this.listener = listener;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (listener != null) {
                listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {

            this.position = position;
            if (listener != null) {
                listener.onPageScrolled(position, position, position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {

                if (position == 0) {//D 索引为0{
                    setCurrentItem(getAdapter().getCount() - 2, false);
                } else if (position == getAdapter().getCount() - 1) {//A
                    setCurrentItem(1, false);
                }
            }

            if (listener != null) {
                listener.onPageScrollStateChanged(state);
            }
        }
    }

    class InnerPagerAdapter extends PagerAdapter {

        private PagerAdapter adapter;

        public InnerPagerAdapter(PagerAdapter adapter) {

            this.adapter = adapter;
        }

        @Override
        public int getCount() {
            return adapter.getCount() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return adapter.isViewFromObject(view, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // position   [DABCDA]
            //转换为原来集合中的索引
            if (position == 0) {
                // 第一个D
                position = adapter.getCount() - 1;
            } else if (position == getCount() - 1) {
                position = 0;
            } else {
                position -= 1;
            }


            return adapter.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            adapter.destroyItem(container, position, object);
        }
    }
}
