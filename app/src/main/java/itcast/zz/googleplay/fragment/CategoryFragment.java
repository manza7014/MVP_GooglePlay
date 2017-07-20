package itcast.zz.googleplay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itcast.zz.googleplay.utils.UIUtils;
import itcast.zz.googleplay.view.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 * 分类
 */
public class CategoryFragment extends BaseFragment {



    protected int load() {
        return LoadingPage.STATE_ERROR;
    }

    protected View createSuccessView() {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setText("请求数据成功");
        textView.setTextSize(20);// 20px
        return textView;
    }

}
