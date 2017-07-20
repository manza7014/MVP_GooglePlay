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
public class DetailProtocol extends BaseProtocol<AppInfo> {
    private String packageName;

    public DetailProtocol(String packageName) {
        this.packageName = packageName;
    }

    @Override
    protected String getKey() {
        return "detail";//请求网络的关键字
    }

    //额外的参数
    @Override
    protected String getExtrasParams() {
        return "&packageName="+packageName;
    }

    @Override
    protected AppInfo parseJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            AppInfo appInfo = null;
            long id = object.optLong("id");
            String name = object.optString("name");
            String packageName = object.optString("packageName");
            String iconUrl = object.optString("iconUrl");
            double stars = object.optDouble("stars");
            long size = object.optLong("size");
            String downloadUrl = object.optString("downloadUrl");
            String des = object.optString("des");
            String author = object.optString("author");
            String date = object.optString("date");
            String downloadNum = object.optString("downloadNum");
            String version = object.optString("version");


            List<String> screens = new ArrayList<>();
            if (object.has("screen")) {
                JSONArray screenArray = object.optJSONArray("screen");
                for (int i = 0; i < screenArray.length(); i++) {
                    String screen = screenArray.optString(i);
                    screens.add(screen);
                }
            }
            List<String> safeUrls = new ArrayList<>();
            List<String> safeDesUrls = new ArrayList<>();
            List<String> safeDess = new ArrayList<>();
            List<Integer> safeDesColors = new ArrayList<>();
            if (object.has("safe")) {
                JSONArray safeArray = object.optJSONArray("safe");


                JSONObject safeObject;
                for (int i = 0; i < safeArray.length(); i++) {
                    safeObject = safeArray.optJSONObject(i);

                    String safeUrl = safeObject.optString("safeUrl");
                    String safeDesUrl = safeObject.optString("safeDesUrl");
                    String safeDes = safeObject.optString("safeDes");
                    int safeDesColor = safeObject.optInt("safeDesColor");
                    safeUrls.add(safeUrl);
                    safeDesUrls.add(safeDesUrl);
                    safeDess.add(safeDes);
                    safeDesColors.add(safeDesColor);
                }
            }

            appInfo = new AppInfo(id, name, packageName, iconUrl, stars, size, downloadUrl, des,
                    author, date, downloadNum, version, screens, safeUrls, safeDesUrls, safeDess,
                    safeDesColors);
            return appInfo;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
