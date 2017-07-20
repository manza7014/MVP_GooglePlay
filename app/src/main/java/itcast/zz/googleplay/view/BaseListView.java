package itcast.zz.googleplay.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ListView;

import itcast.zz.googleplay.R;
import itcast.zz.googleplay.utils.UIUtils;

/**
 * Created by wangx on 2016/7/30.
 */
public class BaseListView extends ListView {
    public BaseListView(Context context) {
        this(context,null);

    }

    public BaseListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

//        <ListView
//        android:layout_width="match_parent"
//        android:cacheColorHint="@android:color/transparent"
//        android:divider="@android:color/transparent"
//        android:listSelector="@android:color/transparent"
//        android:layout_height="match_parent"></ListView>
        // 取消低版本滑动的黑色背景
        setCacheColorHint(Color.TRANSPARENT);
        //取消分割线
        setDivider(UIUtils.getDrawable(R.drawable.nothing));
        //取消 item 默认选择器
        setSelector(R.drawable.nothing);
    }
}
