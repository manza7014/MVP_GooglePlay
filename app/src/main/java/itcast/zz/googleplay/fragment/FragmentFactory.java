package itcast.zz.googleplay.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangx on 2016/7/28.
 * 生产Fragment对象
 */
public class FragmentFactory {

    private static final Map<Integer, BaseFragment> FRAGMENT_MAP = new HashMap<>();

    /**
     * 生产Fragment
     *
     * @param position
     * @return
     */
    public static BaseFragment create(int position) {
        //1.从缓存中获取
        BaseFragment fragment = FRAGMENT_MAP.get(position);//

        //2. 如果缓存中的对象为空 重新创建  并且再添加到缓存中
        if (fragment == null) {

            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new AppFragment();
            } else if (position == 2) {
                fragment = new GameFragment();
            } else if (position == 3) {
                fragment = new SubjectFragment();
            } else if (position == 4) {
                fragment = new CategoryFragment();
            } else if (position == 5) {
                fragment = new TopFragment();
            }
            // 缓存到内存中
            if (fragment != null) {
                FRAGMENT_MAP.put(position, fragment);
            }
        }
        return fragment;
    }
}
