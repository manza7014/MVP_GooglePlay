package itcast.zz.googleplay.holder;

import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import itcast.zz.googleplay.R;
import itcast.zz.googleplay.bean.UserInfo;
import itcast.zz.googleplay.protocol.UserProtocol;
import itcast.zz.googleplay.utils.HttpHelper;
import itcast.zz.googleplay.utils.ThreadUtils;
import itcast.zz.googleplay.utils.ToastUtils;
import itcast.zz.googleplay.utils.UIUtils;

/**
 * Created by wangx on 2016/8/1.
 */
public class MenuHolder extends BaseHolder<UserInfo> implements View.OnClickListener {
    private ImageView image_photo;
    private TextView user_name;
    private TextView user_email;
    private RelativeLayout photo_layout;
    private RelativeLayout home_layout;
    private RelativeLayout setting_layout;
    private RelativeLayout theme_layout;
    private RelativeLayout scans_layout;
    private RelativeLayout feedback_layout;
    private RelativeLayout updates_layout;
    private RelativeLayout about_layout;
    private RelativeLayout exit_layout;

    @Override
    protected View initView() {
        //  xml  ----View
        View view = UIUtils.inflate(R.layout.holder_menu);

        photo_layout = (RelativeLayout) view.findViewById(R.id.photo_layout);
        image_photo = (ImageView) view.findViewById(R.id.image_photo);
        user_name = (TextView) view.findViewById(R.id.user_name);
        user_email = (TextView) view.findViewById(R.id.user_email);
        photo_layout.setOnClickListener(this);
        return view;
    }

    @Override
    protected void refreshView(UserInfo userInfo) {
        user_name.setText(userInfo.name);
        user_email.setText(userInfo.email);

        Picasso.with(UIUtils.getContext())
                .load(HttpHelper.BASEURL+"/image?name="+userInfo.url)
                .into(image_photo);
    }

    @Override
    public void onClick(View v) {

        ThreadUtils.runOnBackThread(new Runnable() {
            @Override
            public void run() {
               final  UserInfo userInfo = login();

                if (userInfo == null){
                    //请求失败了
                    Looper.prepare();
                    ToastUtils.showToast("登录失败");
                    Looper.loop();
                }else{
                    //成功了
                    ThreadUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            setData(userInfo);// 自动调用refreshView方法
                        }
                    });

                }
            }
        });
    }

    private UserInfo login() {
        // 请求数据 解析数据  缓存数据  复用缓存数据
        UserProtocol protocol = new UserProtocol();
        UserInfo userinfo = protocol.load(0);
        return userinfo;
    }
}
