package test.launcher.mummu.androidscreenrecordsecond.utils;/**
 * Created by muhammed on 7/20/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class RecordPreferance {
    public static final String PREFERENCE_NAME = "AndroidScreenRecordSecond";

    /**
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    private static void insertStringData(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    private static void insertBooleanData(Context context, String key, boolean value) {
        getSharedPreference(context).edit().putBoolean(key, value).apply();
    }

    /**
     * @param context
     * @param key
     * @return
     */
    private static String getStringData(Context context, String key) {
        return getSharedPreference(context).getString(key, "");
    }

    /**
     * @param context
     * @param key
     * @return
     */
    private static boolean getBooleanData(Context context, String key) {
        return getSharedPreference(context).getBoolean(key, false);
    }
}
