package test.launcher.mummu.androidscreenrecordsecond.utils;/**
 * Created by muhammed on 7/20/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class RecordPreference {
    public static final String PREFERENCE_NAME = "AndroidScreenRecordSecond";
    public static final String AUDIO_RECORD_ENNABLED = "audio_value";
    public static final String CAMERA_ENABLED = "camera_enabled";
    public static final String WATER_MARK_ENABLE = "watermark_enabled";
    public static final String COLOR_ID = "color_id";
    public static final String WATER_MARK_TEXT = "water_mark_text";
    public static final String WATER_MARK_TEXT_COLOR = "watermark_text_color";
    public static final String WATER_MARK_TEXT_SIZE = "watermark_text_size";
    public static final String NOTIFICATION_ENABLED = "notification_enabled";

    /**
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void insertStringData(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    public static void insertBooleanData(Context context, String key, boolean value) {
        getSharedPreference(context).edit().putBoolean(key, value).apply();
    }

    public static void insertIntData(Context context, String key, int value) {
        getSharedPreference(context).edit().putInt(key, value).apply();
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static String getStringData(Context context, String key) {
        return getSharedPreference(context).getString(key, "");
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public static boolean getBooleanData(Context context, String key) {
        return getSharedPreference(context).getBoolean(key, false);
    }

    public static int getIntData(Context context, String key) {
        return getSharedPreference(context).getInt(key, 1);
    }
}
