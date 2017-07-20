package itcast.zz.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import itcast.zz.googleplay.holder.BaseHolder;
import itcast.zz.googleplay.holder.MoreHolder;
import itcast.zz.googleplay.utils.ThreadUtils;

/**
 * Created by wangx on 2016/7/30.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter implements AdapterView.OnItemClickListener {
    private static final int ITEM_MORE = 0;//加载更多的条目类型
    private static final int ITEM_DEFAULT = 1;//默认的条目类型
   protected List<T> datas;
    private ListView listView;
    private MoreHolder moreHolder;

    public DefaultAdapter(List<T> datas, ListView listView) {

        this.datas = datas;
        this.listView = listView;
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        position -= listView.getHeaderViewsCount();// 头布局的数量

        onInnerIntemClick(position);
    }



    // 返回listview的条目数量
    @Override
    public int getCount() {
        // 不加健壮性判断
        return datas.size() + 1;  // + 将加载更多数据的条目加上去
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回listview 行视图种类数量
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;//TODO 坑
    }

    // 根据position返回条目视图的类型　   0,1     0,1,2
    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            //最后一个条目 加载更多
            return ITEM_MORE;
        }
        return getInnerItemViewType(position);
    }

    private int getInnerItemViewType(int position) {
        return ITEM_DEFAULT;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;

        switch (getItemViewType(position)) {
            case ITEM_MORE://加载更多
                if (convertView == null) {
                    holder = getMoreHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }

                break;
            default://默认
                if (convertView == null) {
                    holder = getHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                T t = datas.get(position);
                holder.setData(t);
                break;
        }


        return holder.getContentView();
    }

    private BaseHolder getMoreHolder() {
        if (moreHolder == null)
            moreHolder = new MoreHolder(this);
        return moreHolder;
    }

    /**
     * 加载更多数据 //请求网络添加到来的集合中 notifyDataSetChange
     */
    public void onload() {
        ThreadUtils.runOnBackThread(new Runnable() {
            @Override
            public void run() {
                final List<T> newDatas = loadMore();

                ThreadUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newDatas == null) {
                            //请求更多失败
                            moreHolder.setData(MoreHolder.ERROR);
                        } else {
                            if (newDatas.size() == 0) {
                                //没有更多数据
                                moreHolder.setData(MoreHolder.HAS_NO_MORE);
                            } else {
                                //将请求到的数据添加到原来的集合中  notifyDataSetChange
                                moreHolder.setData(MoreHolder.HAS_MORE);
                                datas.addAll(newDatas);
                                //修改UI
                                notifyDataSetChanged();
                            }
                        }
                    }
                });
            }


        });
    }

    /**
     * 条目的点击事件
     * @param position
     */
    protected  void onInnerIntemClick(int position){};
    /**
     * 请求更多数据
     *
     * @return
     */
    protected abstract List<T> loadMore();

    /**
     * 返回对应的holder对象
     *
     * @return
     */
    protected abstract BaseHolder<T> getHolder();


}
