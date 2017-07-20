package itcast.zz.googleplay.bean;

import java.util.List;

/**
 * Created by wangx on 2016/7/29.
 * 实体类
 */
public class AppInfo {

    // 内省操作 没有了

    public long id;
    public String name;
    public String packageName;
    public String iconUrl;
    public double stars;
    public long size;
    public String downloadUrl;
    public String des;


    ///////////////////////////////////////////////////////////////////////////
    // 详情界面新增的字段
    ///////////////////////////////////////////////////////////////////////////

    public String author;
    public String date;
    public String downloadNum;
    public String version;
    public List<String> screens;
    public List<String> safeUrls;
    public List<String> safeDesUrls;
    public List<String> safeDess;
    public List<Integer> safeDesColors;

    public AppInfo(long id, String name,
                   String packageName,
                   String iconUrl,
                   double stars,
                   long size,
                   String downloadUrl,
                   String des) {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.iconUrl = iconUrl;
        this.stars = stars;
        this.size = size;
        this.downloadUrl = downloadUrl;
        this.des = des;
    }

    public AppInfo(long id, String name, String packageName, String iconUrl, double stars, long size,
                   String downloadUrl, String des, String author, String date, String downloadNum,
                   String version, List<String> screens, List<String> safeUrls, List<String> safeDesUrls,
                   List<String> safeDess, List<Integer> safeDesColors) {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.iconUrl = iconUrl;
        this.stars = stars;
        this.size = size;
        this.downloadUrl = downloadUrl;
        this.des = des;
        this.author = author;
        this.date = date;
        this.downloadNum = downloadNum;
        this.version = version;
        this.screens = screens;
        this.safeUrls = safeUrls;
        this.safeDesUrls = safeDesUrls;
        this.safeDess = safeDess;
        this.safeDesColors = safeDesColors;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", stars=" + stars +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
