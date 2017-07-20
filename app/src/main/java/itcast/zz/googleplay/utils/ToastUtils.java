package itcast.zz.googleplay.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wangx on 2016/7/28.
 */
public class ToastUtils {

    private static Toast toast;

    /**
     * 静态toast
     *
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);

        toast.setText(text);
        toast.show();
    }

    public static void showToast(String text){
        showToast(UIUtils.getContext(),text);
    }
}
