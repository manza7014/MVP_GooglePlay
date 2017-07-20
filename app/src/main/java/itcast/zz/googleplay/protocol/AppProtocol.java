package itcast.zz.googleplay.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import itcast.zz.googleplay.bean.AppInfo;

/**
 * Created by wangx on 2016/8/1.
 */
public class AppProtocol extends BaseProtocol<List<AppInfo>> {

    @Override
    protected String getKey() {
        return "app";//请求网络的关键字
    }

    @Override
    protected List<AppInfo> parseJson(String json) {
        // [   jsonArray
        //{   jsonObject
        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject;
            AppInfo appInfo = null;
            List<AppInfo> appInfos = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.optJSONObject(i);
                long id = jsonObject.optLong("id");
                String name = jsonObject.optString("name");
                String packageName = jsonObject.optString("packageName");
                String iconUrl = jsonObject.optString("iconUrl");
                double stars = jsonObject.optDouble("stars");
                long size = jsonObject.optLong("size");
                String downloadUrl = jsonObject.optString("downloadUrl");
                String des = jsonObject.optString("des");


                appInfo = new AppInfo(id, name, packageName, iconUrl, stars, size, downloadUrl, des);
                appInfos.add(appInfo);
            }

            return appInfos;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
