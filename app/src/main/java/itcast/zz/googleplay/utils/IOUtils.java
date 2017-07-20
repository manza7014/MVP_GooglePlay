package itcast.zz.googleplay.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by wangx on 2016/7/29.
 * 释放资源
 */
public class IOUtils {
    /**
     * 释放资源
     * @param closeable
     */
    public static  void cloce(Closeable closeable){
        if (closeable!= null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
