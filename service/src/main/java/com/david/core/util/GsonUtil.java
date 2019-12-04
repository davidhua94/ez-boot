package com.david.core.util;

import com.google.gson.Gson;

/**
 * @author David hua
 * @date 2019-08-18 21:48
 */
public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }


    public static String obj2Str(Object obj) {
        return gson.toJson(obj);
    }

    public static<T> T str2Obj(String jsonStr, Class<T> clazz) {
        return gson.fromJson(jsonStr, clazz);
    }
}
