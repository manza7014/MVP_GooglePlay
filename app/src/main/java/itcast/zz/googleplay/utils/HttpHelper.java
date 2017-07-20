package itcast.zz.googleplay.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by wangx on 2016/7/29.
 * 请求网络数据的操作
 */
public class HttpHelper {
    public static final String  BASEURL= "http://127.0.0.1:8090";
    private OkHttpClient okHttpClient = new OkHttpClient();
    String url;

    public HttpHelper(String url) {
        this.url = url;
    }

    /**
     * 同步的get请求
     * @return
     */
    public String getSync(){
        //请求对象
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();//同步方法
            if (response.isSuccessful()){
                return response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 异步的get请求
     * @param callback
     */
    public void getAsync(Callback callback){
        //请求对象
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(callback);
    }
}
