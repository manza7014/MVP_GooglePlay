package itcast.zz.googleplay.holder;

import android.view.View;

/**
 * Created by wangx on 2016/7/30.
 */
public abstract class BaseHolder<Data> {
    private final View contentView;

    private Data data;

    public BaseHolder() {
        contentView = initView();
        contentView.setTag(this);
    }


    public View getContentView() {
        return contentView;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
        refreshView(data);
    }

    /**
     * 1.创建view对象
     * 2.初始化操作  findviewById
     *
     * @return
     */
    protected abstract View initView();

    /**
     * 将数据显示到对应的控件上
     */
    protected abstract void refreshView(Data data);
}
