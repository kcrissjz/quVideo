package com.fsd.quvideo.util;

import android.util.Log;

/**
 * 日志工具类
 * 可以输出具体的类和行数
 */
public class LogHelper {

    private static boolean IS_DEBUG_MODE = true;
    public static final String TAG_PREFIX = "LogHelper";

    public static void setDebugMode(boolean isDebugMode) {
        IS_DEBUG_MODE = isDebugMode;
    }

    //------------- verbose -------------
    public static void v(Object message) {
        v(null, message, 1);
    }

    public static void v(String tag, Object message) {
        v(tag, message, 1);
    }

    public static void v(Object message, int extraLevel) {
        v(null, message, ++extraLevel);
    }

    public static void v(String tag, Object message, int extraLevel) {
        if (IS_DEBUG_MODE)
            Log.v(generateTag(tag, extraLevel), message.toString());
    }

    //------------- info -------------
    public static void i(Object message) {
        i(null, message, 1);
    }

    public static void i(String tag, Object message) {
        i(tag, message, 1);
    }

    public static void i(Object message, int extraLevel) {
        i(null, message, ++extraLevel);
    }

    public static void i(String tag, Object message, int extraLevel) {
        if (IS_DEBUG_MODE)
            Log.i(generateTag(tag, extraLevel), message.toString());
    }

    //------------- debug -------------
    public static void d(Object message) {
        d(null, message, 1);
    }

    public static void d(String tag, Object message) {
        d(tag, message, 1);
    }

    public static void d(Object message, int extraLevel) {
        d(null, message, ++extraLevel);
    }

    public static void d(String tag, Object message, int extraLevel) {
        if (IS_DEBUG_MODE)
            Log.d(generateTag(tag, extraLevel), message.toString());
    }

    //------------- warn -------------
    public static void w(Object message) {
        w(null, message, 1);
    }

    public static void w(String tag, Object message) {
        w(tag, message, 1);
    }

    public static void w(Object message, int extraLevel) {
        w(null, message, ++extraLevel);
    }

    public static void w(String tag, Object message, int extraLevel) {
        if (IS_DEBUG_MODE)
            Log.w(generateTag(tag, extraLevel), message.toString());
    }

    //------------- error -------------
    public static void e(Object message) {
        e(null, message, 1);
    }

    public static void e(String tag, Object message) {
        e(tag, message, 1);
    }

    public static void e(Object message, int extraLevel) {
        e(null, message, ++extraLevel);
    }

    public static void e(String tag, Object message, int extraLevel) {
        if (IS_DEBUG_MODE)
            Log.e(generateTag(tag, extraLevel), message.toString());
    }

    private static String generateTag(String tag, int extraLevel) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2 + extraLevel];
        String className = stackTraceElement.getClassName();
        return String.format("%s:[%s.%s():%s] %s",
                TAG_PREFIX,
                className.substring(className.lastIndexOf(".") + 1),
                stackTraceElement.getMethodName(),
                stackTraceElement.getLineNumber(),
                tag == null ? "" : tag);
    }
}