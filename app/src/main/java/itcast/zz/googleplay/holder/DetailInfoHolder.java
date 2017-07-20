package itcast.zz.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import itcast.zz.googleplay.bean.AppInfo;
import itcast.zz.googleplay.utils.UIUtils;

/**
 * Created by wangx on 2016/8/1.
 */
public class DetailInfoHolder extends BaseHolder<AppInfo> {

    private TextView textView;

    @Override
    protected View initView() {
        textView = new TextView(UIUtils.getContext());
        return textView; // 创建view对象  初始化操作
    }

    @Override
    protected void refreshView(AppInfo appInfo) {
//将数据展示到界面上
        textView.setText(appInfo.name);
    }
}
