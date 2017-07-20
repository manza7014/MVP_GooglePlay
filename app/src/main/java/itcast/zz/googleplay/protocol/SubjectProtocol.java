package itcast.zz.googleplay.protocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import itcast.zz.googleplay.bean.SubjectInfo;

/**
 * Created by wangx on 2016/7/30.
 * 1.请求服务器数据 2.缓存数据 3.解析数据 4.复用缓存数据
 */
public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>> {

    //请求网络关键字
    //http://127.0.0.1:8090/subject?index=0
    @Override
    protected String getKey() {
        return "subject";
    }

    @Override
    protected List<SubjectInfo> parseJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            //
            JSONObject jsonObject;
            SubjectInfo subjectInfo = null;
            List<SubjectInfo> subjectInfos = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.optJSONObject(i);
                String des = jsonObject.optString("des");
                String url = jsonObject.optString("url");

                subjectInfo = new SubjectInfo(des, url);
                subjectInfos.add(subjectInfo);
            }

            return subjectInfos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
