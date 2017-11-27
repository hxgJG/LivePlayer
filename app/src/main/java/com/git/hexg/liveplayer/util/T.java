package com.git.hexg.liveplayer.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Toast统一管理类
 */
public class T {
    private static Toast toast;

    private T() {}

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        try {
            if (null == toast) {
                toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
            }
            toast.show();
        } catch (Exception e) {

        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
            toast = null;
        }
    }
}
