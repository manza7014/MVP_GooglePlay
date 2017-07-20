package itcast.zz.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import itcast.zz.googleplay.R;
import itcast.zz.googleplay.adapter.DefaultAdapter;
import itcast.zz.googleplay.bean.SubjectInfo;
import itcast.zz.googleplay.holder.BaseHolder;
import itcast.zz.googleplay.protocol.SubjectProtocol;
import itcast.zz.googleplay.utils.HttpHelper;
import itcast.zz.googleplay.utils.UIUtils;
import itcast.zz.googleplay.view.BaseListView;

/**
 * A simple {@link Fragment} subclass.
 * 专题
 */
public class SubjectFragment extends BaseFragment {


    private List<SubjectInfo> datas;

    @Override
    protected int load() {
        // 1.请求服务器数据 2.缓存数据 3.解析数据 4.复用缓存数据
        SubjectProtocol protocol = new SubjectProtocol();
        //请求数据
        datas = protocol.load(0);

        return checkDatas(datas);//LoadingPage.STATE_ERROR;
    }

    /**
     * 当服务器返回的状态为 success
     *
     * @return
     */
    @Override
    protected View createSuccessView() {
        ListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new SubjectAdapter(datas,listView));

        return listView;
    }

    public class SubjectAdapter extends DefaultAdapter<SubjectInfo> {


        public SubjectAdapter(List<SubjectInfo> datas,ListView listView) {
            super(datas,listView);
        }



        @Override
        protected BaseHolder<SubjectInfo> getHolder() {
            return new SubjectViewHolder();
        }

        @Override
        protected List<SubjectInfo> loadMore() {
            SubjectProtocol protocol = new SubjectProtocol();
            //请求数据
            List<SubjectInfo> newDatas = protocol.load(datas.size());
            return newDatas;
        }
    }

    public class SubjectViewHolder extends BaseHolder<SubjectInfo> {
        public ImageView item_icon;
        public TextView item_txt;


        @Override
        protected View initView() {
            View contentView = UIUtils.inflate(R.layout.item_subjectinfo);
            this.item_icon = (ImageView) contentView.findViewById(R.id.item_icon);
            this.item_txt = (TextView) contentView.findViewById(R.id.item_txt);
            return contentView;
        }


        @Override
        protected void refreshView(SubjectInfo subjectInfo) {
            Picasso.with(UIUtils.getContext())
                    .load(HttpHelper.BASEURL + "/image?name=" + subjectInfo.url)
                    .into(this.item_icon);
            this.item_txt.setText(subjectInfo.des);

        }
    }
}
