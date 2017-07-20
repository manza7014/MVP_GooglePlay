package itcast.zz.googleplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import itcast.zz.googleplay.adapter.BaseListAdapter;
import itcast.zz.googleplay.bean.AppInfo;
import itcast.zz.googleplay.holder.HomePictureHolder;
import itcast.zz.googleplay.protocol.HomeProtocol;
import itcast.zz.googleplay.utils.UIUtils;
import itcast.zz.googleplay.view.BaseListView;

/**
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class HomeFragment extends BaseFragment {

    private List<AppInfo> datas;
    private List<String> pictures;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();//主动请求网络数据
    }

    protected int load() {
        // 1. 请求数据 2.缓存 3.解析数据 4.复用缓存数据( 协议对象)
        HomeProtocol protocol = new HomeProtocol();
        datas = protocol.load(0);
        pictures = protocol.getPictures();//轮播图的url
        return checkDatas(datas);//LoadingPage.STATE_SUCCESS;
    }


    /**
     * 只有返回的状态 为 STATE_SUCCESS 才会调用
     *
     * @return
     */
    protected View createSuccessView() {
        ListView listView = new BaseListView(UIUtils.getContext());

        HomePictureHolder homePictureHolder = new HomePictureHolder();

        homePictureHolder.setData(pictures);

        //添加头部据
        listView.addHeaderView(homePictureHolder.getContentView());

        listView.setAdapter(new BaseListAdapter(datas,listView) {
            /**
             * 请求更多数据
             * @return
             */
            @Override
            protected List<AppInfo> loadMore() {
                // 1.请求服务器数据 2.缓存  3.解析 4.复用缓存
                HomeProtocol protocol = new HomeProtocol();
                List<AppInfo> newDatas = protocol.load(datas.size());
                return newDatas;
            }


        });
        return listView;
    }


}
