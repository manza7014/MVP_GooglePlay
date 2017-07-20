package itcast.zz.googleplay.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import itcast.zz.googleplay.bean.AppInfo;

/**
 * Created by wangx on 2016/7/29.
 * 首页的协议对象
 * 1. 请求网络数据 2.缓存到本地 3.解析  4.复用缓存数据
 */
public class HomeProtocol extends BaseProtocol<List<AppInfo>> {

    private List<String> pictures;

    @Override
    protected String getKey() {
        return "home";
    }


    /**
     * 返回轮播图的url
     * @return
     */
    public List<String> getPictures() {
        return pictures;
    }

    /***
     * 解析json
     *
     * @param json
     */
    @Override
    protected List<AppInfo> parseJson(String json) {
// gson  1/6  1  6
//        {// jsonObject
        //[ JsonArray
        try {
            JSONObject jsonObject = new JSONObject(json);
            /*"picture": [
            "image/home01.jpg",
                    "image/home02.jpg",
                    "image/home03.jpg",
                    "image/home04.jpg",
                    "image/home05.jpg",
                    "image/home06.jpg",
                    "image/home07.jpg",
                    "image/home08.jpg"
            ],*/
            JSONArray pictureArray = jsonObject.optJSONArray("picture");

            pictures = new ArrayList<>();
            for (int i = 0; i < pictureArray.length(); i++) {
                String s = pictureArray.optString(i);
                pictures.add(s);
            }

            JSONArray listArray = jsonObject.optJSONArray("list");
            AppInfo appInfo = null;
            List<AppInfo> appInfos = new ArrayList<>();
            JSONObject object; //在栈内存
            for (int i = 0; i < listArray.length(); i++) {
                object = listArray.getJSONObject(i);
                long id = object.optLong("id");
                String name = object.optString("name");
                String packageName = object.optString("packageName");
                String iconUrl = object.optString("iconUrl");
                double stars = object.optDouble("stars");
                long size = object.optLong("size");
                String downloadUrl = object.optString("downloadUrl");
                String des = object.optString("des");

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
