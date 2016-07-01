package cn.encore.framecommon.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by：Encore
 * Created Time：16/7/1 16:13
 */
public class SpUtils {
    private SharedPreferences sp = null;
    private SharedPreferences.Editor edit = null;
    private static Context mContext;

    private static class LazyHolder {
        private static final SpUtils INSTANCE = new SpUtils(mContext);
    }

    public static SpUtils getInstance(Context context) {
        mContext = context.getApplicationContext();
        return LazyHolder.INSTANCE;
    }

    private SpUtils(Context context) {
        this(context.getApplicationContext().getSharedPreferences("sp",Context.MODE_PRIVATE));
    }

    private SpUtils(SharedPreferences sp) {
        this.sp = sp;
        edit = sp.edit();
    }

    public void setValue(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.commit();
    }

    public void setValue(String key, float value) {
        edit.putFloat(key, value);
        edit.commit();
    }

    public void setValue(String key, int value) {
        edit.putInt(key, value);
        edit.commit();
    }

    public void setValue(String key, long value) {
        edit.putLong(key, value);
        edit.commit();
    }

    public void setValue(String key, String value) {
        edit.putString(key, value);
        edit.commit();
    }

    public boolean getBoolValue(String key) {
        return sp.getBoolean(key, false);
    }

    public float getFloatValue(String key) {
        return sp.getFloat(key, 0);
    }

    public int getIntegerValue(String key) {
        return sp.getInt(key, 0);
    }

    public long getLongValue(String key) {
        return sp.getLong(key, 0);
    }

    public String getStringValue(String key) {
        return sp.getString(key, "");
    }

    public void remove(String key) {
        edit.remove(key);
        edit.commit();
    }

    public void clear() {
        edit.clear();
        edit.commit();
    }

    public boolean contains(String s) {
        return sp.contains(s);
    }
}
