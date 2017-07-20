package itcast.zz.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import itcast.zz.googleplay.adapter.BaseListAdapter;
import itcast.zz.googleplay.bean.AppInfo;
import itcast.zz.googleplay.protocol.GameProtocol;
import itcast.zz.googleplay.utils.UIUtils;
import itcast.zz.googleplay.view.BaseListView;

/**
 * A simple {@link Fragment} subclass.
 * 游戏
 */
public class GameFragment extends BaseFragment {


    private List<AppInfo> datas;

    protected int load() {
        GameProtocol protocol = new GameProtocol();
        datas = protocol.load(0);
        return checkDatas(datas);//LoadingPage.STATE_ERROR;
    }

    protected View createSuccessView() {
        ListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new BaseListAdapter(datas,listView) {

            @Override
            protected List<AppInfo> loadMore() {
                GameProtocol protocol = new GameProtocol();
                List<AppInfo> newDatas= protocol.load(datas.size());
                return newDatas;
            }
        });
        return listView;
    }

}
