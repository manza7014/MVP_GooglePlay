package itcast.zz.googleplay.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import itcast.zz.googleplay.utils.HttpHelper;
import itcast.zz.googleplay.utils.UIUtils;
import itcast.zz.googleplay.view.CyclerViewPager;

/**
 * Created by wangx on 2016/8/1.
 */
public class HomePictureHolder extends BaseHolder<List<String>> {

    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected View initView() {
        FrameLayout frameLayout = new FrameLayout(UIUtils.getContext());
        viewPager = new CyclerViewPager(UIUtils.getContext());
        frameLayout.addView(viewPager, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(134)));// 200px  dp
         return frameLayout;//创建view对象 初始化view对象
    }

    //将数据显示到对应的控件上
    @Override
    protected void refreshView(final List<String> urls) {
        // [ABCD]   [DABCDA]
// position   [DABCDA]
//转换为原来集合中的索引
// 第一个D
// imageView添加到viewpager中
// imageview设置数据
        adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return urls.size();  // [ABCD]   [DABCDA]
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override

            public Object instantiateItem(ViewGroup container, int position) {


                ImageView imageView = new ImageView(UIUtils.getContext());

                // imageView添加到viewpager中
                container.addView(imageView);
                // imageview设置数据

                Picasso.
                        with(UIUtils.getContext())
                        .load(HttpHelper.BASEURL + "/image?name=" + urls.get(position))
                        .into(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };
        viewPager.setAdapter(adapter);

    }
}
