package itcast.zz.googleplay.holder;

import android.view.View;
import android.widget.RelativeLayout;

import itcast.zz.googleplay.R;
import itcast.zz.googleplay.adapter.DefaultAdapter;
import itcast.zz.googleplay.utils.UIUtils;

/**
 * Created by wangx on 2016/7/30.
 */
public class MoreHolder extends BaseHolder<Integer> {
    public static final int HAS_MORE = 0;//有更多数据
    public static final int ERROR = 1;//加载失败
    public static final int HAS_NO_MORE = 2;//没有更多数据
    private DefaultAdapter adapter;
    private RelativeLayout rl_more_loading;
    private RelativeLayout rl_more_error;

    public MoreHolder(DefaultAdapter adapter) {

        this.adapter = adapter;
    }


    @Override
    protected View initView() {
        View view = UIUtils.inflate(R.layout.item_more);
        //加载中的布局
        rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        return view;
    }

    @Override
    public View getContentView() {
        // 需要加载更多数据  最后一个条目显示的时候
        //在 adapter 中调用  getView方法的时候   就是一个条目显示的时候
        onload();
        return super.getContentView();
    }

    private void onload() { //将请求到的数据添加到原来的集合中 adapter.notifyDataSetChange
        //请求更多数据
        if (adapter != null) {
            adapter.onload();//将加载更多数据交给adapter处理
        }
    }

    @Override
    protected void refreshView(Integer state) {
// state 加载更多的状态
        if (rl_more_loading != null) {
            rl_more_loading.setVisibility(state == HAS_MORE ? View.VISIBLE : View.GONE);
        }

        if (rl_more_error != null) {
            rl_more_error.setVisibility(state == ERROR ? View.VISIBLE : View.GONE);
        }
    }
}
