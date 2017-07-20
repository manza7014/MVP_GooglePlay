package itcast.zz.googleplay.protocol;

import android.os.SystemClock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import itcast.zz.googleplay.utils.FileUtils;
import itcast.zz.googleplay.utils.HttpHelper;
import itcast.zz.googleplay.utils.IOUtils;
import itcast.zz.googleplay.utils.LogUtil;

/**
 * Created by wangx on 2016/7/29.
 * 协议对象
 * 1. 请求网络数据 2.缓存到本地 3.解析  4.复用缓存数据
 * 1. 类上声明: 类中的属性 方法 都可以使用
 * 2.在方法上声明:  智能在本方法中使用
 *
 * 你的load方法 返回什么  泛型就写什么(泛型 反射 注解)
 */
public abstract class BaseProtocol<T> {

    public T load(int index) {

        SystemClock.sleep(2000);
        //1. 从缓存中获取数据
        String json = loadFromLocal(index);
        if (json == null) {
            //请求服务器数据
            json = loadFromServer(index);
            if (json != null) {
                //缓存到本地
                save2Local(json, index);
            }
        } else {
            LogUtil.d("%s", "复用了缓存数据...");
        }
        if (json != null) {
            return parseJson(json);
        }
        return null;
    }


    /***
     * 从服务器端请求数据
     *
     * @param index
     * @return
     */
    private String loadFromServer(int index) {
        // HttpClient api>9 使用HttpUrlConnect android 6.0
        //HttpUrlConnection

        // 1.Volley 2.OkHttp  3.Retrofit 4. NoHttp
        // 127.0.0.1:8090/home?index=0  /baseurl:8090/subject?index=0
        HttpHelper http = new HttpHelper(HttpHelper.BASEURL + "/"+getKey()+"?index=" + index+getExtrasParams());
        String json = http.getSync();
        return json;
    }

    /**
     * 返回额外的参数
     * @return
     */
    protected String getExtrasParams() {
        return "";    //  &packageName=packageName
    }


    /***
     * 将数据缓存到本地
     * 1. json 直接缓存到本地   1.过期时间 2.md5
     * 2. 将数据存到数据库中
     *
     * @param json
     * @param index
     */
    private void save2Local(String json, int index) {
        //在第一行增加一个过期时间
        //如果 读取到过期时间> 当时时间  还没有过期
        //如果读取到的过期时间< 当时时间 过期了 重新请求服务器数据

        File dir = FileUtils.getCache();  // /mnt/sdcard 不需要写sd卡的权限
        File file = new File(dir, getKey()+getExtrasParams()+"_" + index);

        // 字节流 文件 音视频
        // 字符流  文本信息
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
//            fw.write(System.currentTimeMillis()+100*1000+"");//大概一分半 之后过期
            bw = new BufferedWriter(fw);
            bw.write(System.currentTimeMillis() + 100 * 1000 + "");//大概一分半 之后过期
            //换行
            bw.newLine();
            bw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.cloce(bw);
            IOUtils.cloce(fw);
        }
    }

    /**
     * 从本地获取数据
     *
     * @param index
     * @return
     */
    private String loadFromLocal(int index) {
        //读取第一行过期时间
        //如果 读取到过期时间> 当时时间  还没有过期
        //如果读取到的过期时间< 当时时间 过期了 重新请求服务器数据

        File dir = FileUtils.getCache();  // /mnt/sdcard 不需要写sd卡的权限
        File file = new File(dir,  getKey()+getExtrasParams()+"_" + index);
        StringWriter sw = null;
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            long outofTime = Long.parseLong(br.readLine());//获取第一行的过期时间
            if (outofTime < System.currentTimeMillis()) {
                //过期
                return null;
            } else {
                String line;
                //  bytearrayoutputStream
                // 将字符串写到内存中
                sw = new StringWriter();

                while ((line = br.readLine()) != null) {
                    sw.write(line);//将数据写到内存中
                }

                return sw.toString();// 将内存中的数据返回
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.cloce(sw);
            IOUtils.cloce(br);
            IOUtils.cloce(fr);
        }

        return null;
    }



    /**
     * 返回请求网络的关键字   home  subject
     * @return
     */
    protected abstract String getKey() ;


    /***
     * 解析json
     *
     * @param json
     */
    protected abstract T parseJson(String json)  ;

}
