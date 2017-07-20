package itcast.zz.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import itcast.zz.googleplay.R;
import itcast.zz.googleplay.bean.AppInfo;
import itcast.zz.googleplay.holder.DetailInfoHolder;
import itcast.zz.googleplay.protocol.DetailProtocol;
import itcast.zz.googleplay.utils.UIUtils;
import itcast.zz.googleplay.view.LoadingPage;

/**
 * Created by wangx on 2016/8/1.
 */
public class DetailFragment extends BaseFragment {
    private String packageName;
    private FrameLayout detail_info;
    private FrameLayout detail_safe;
    private FrameLayout detail_des;
    private FrameLayout bottom_layout;
    private HorizontalScrollView detail_screen;
    private AppInfo data;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        packageName = bundle.getString("packageName");
        //fragment支持 菜单 支持toolbar
        setHasOptionsMenu(true);

        show();//主动请求网络数据
    }

    @Override
    protected int load() {
        //请求网络数据
        DetailProtocol protocol = new DetailProtocol(packageName);
        data = protocol.load(0);
        if (data == null) {
            return LoadingPage.STATE_ERROR;

        } else {
            return LoadingPage.STATE_SUCCESS;
        }
    }

    @Override
    protected View createSuccessView() {
        // 创建成功界面
        View view = UIUtils.inflate(R.layout.fragment_detail);

        //详情信息


        detail_info = (FrameLayout) view.findViewById(R.id.detail_info);
        DetailInfoHolder detailInfoHolder = new DetailInfoHolder();
        //设置数据
        detailInfoHolder.setData(data);
        //3.将holder维护的view对象 添加到帧布局中
        detail_info.addView(detailInfoHolder.getContentView());

        detail_safe = (FrameLayout) view.findViewById(R.id.detail_safe);
        detail_des = (FrameLayout) view.findViewById(R.id.detail_des);
        bottom_layout = (FrameLayout) view.findViewById(R.id.bottom_layout);
        detail_screen = (HorizontalScrollView) view.findViewById(R.id.detail_screen);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_activity_main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
