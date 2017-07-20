package itcast.zz.googleplay.adapter;

import android.content.Intent;
import android.widget.ListView;

import java.util.List;

import itcast.zz.googleplay.DetailActivity;
import itcast.zz.googleplay.bean.AppInfo;
import itcast.zz.googleplay.holder.BaseHolder;
import itcast.zz.googleplay.holder.BaseListHolder;
import itcast.zz.googleplay.utils.ToastUtils;
import itcast.zz.googleplay.utils.UIUtils;

public abstract class BaseListAdapter extends DefaultAdapter<AppInfo> {
    public BaseListAdapter(List<AppInfo> datas, ListView listView) {
        super(datas,listView);
    }


    @Override
    protected BaseHolder<AppInfo> getHolder() {
        return new BaseListHolder();
    }

    /**
     * 条目点击
     * @param position
     */
    @Override
    protected void onInnerIntemClick(int position) {
        super.onInnerIntemClick(position);
        ToastUtils.showToast("BaseListAdapter Position = "+position);

        Intent intent = new Intent(UIUtils.getContext(), DetailActivity.class);
        intent.putExtra("packageName", datas.get(position).packageName);
        UIUtils.startActivity(intent);
    }
}