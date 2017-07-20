package itcast.zz.googleplay.protocol;

import org.json.JSONException;
import org.json.JSONObject;

import itcast.zz.googleplay.bean.UserInfo;

/**
 * Created by wangx on 2016/8/1.
 */
public class UserProtocol extends BaseProtocol<UserInfo>{
    @Override
    protected String getKey() {
        return "user"; //请求网络的关键字
    }

    @Override
    protected UserInfo parseJson(String json) {
        //   {name:'传智黄盖',email:'huanggai@itcast.cn',url:'image/user.png'}
        try {
            JSONObject jsonObject = new JSONObject(json);
            String name = jsonObject.optString("name");
            String email = jsonObject.optString("email");
            String url = jsonObject.optString("url");
            return new UserInfo(name, email,url);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
