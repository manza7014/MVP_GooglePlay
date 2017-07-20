package itcast.zz.googleplay.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by wangx on 2016/7/29.
 */
public class ViewUtils {
    /**
     * 从父view 中移除
     * @param view
     */
    public static void removeFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            // viewGroup
            if (parent != null && parent instanceof ViewGroup) {//instanceof类属于
                ViewGroup group = (ViewGroup) parent;
                group.removeView(view);
            }

        }
    }
}
