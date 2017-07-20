package itcast.zz.googleplay.bean;

/**
 * Created by wangx on 2016/7/30.
 * 专题界面的实体类
 */
public class SubjectInfo {
   /* "des": "一周新锐游戏精选",
            "url": "image/recommend_01.jpg"*/

    public String des;
    public String url;

    public SubjectInfo(String des, String url) {
        this.des = des;
        this.url = url;
    }

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "des='" + des + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
