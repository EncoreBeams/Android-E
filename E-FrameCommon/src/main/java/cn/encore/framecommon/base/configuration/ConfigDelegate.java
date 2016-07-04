package cn.encore.framecommon.base.configuration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * App兼容委托
 * Created by：Encore
 * Created Time：16/4/23 11:46
 *
 */
public abstract class ConfigDelegate {

    /**
     * Should be called from {@link Activity#onCreate Activity.onCreate()}.
     * <p/>
     * This should be called before {@code super.onCreate()} as so:
     * protected void onCreate(Bundle savedInstanceState) {
     * getDelegate().onCreate(savedInstanceState);
     * super.onCreate(savedInstanceState);
     * // ...
     * }
     */
    public abstract void onCreate(Bundle savedInstanceState);

    /**
     * Should be called from {@link Activity#onStop Activity.onStop()}
     */
    public abstract void onStop();

    /**
     * Should be called from {@link Activity#onDestroy()}
     */
    public abstract void onDestroy();

    /**
     * Should be called from {@link Activity#onPause(), @see android.support.v4.app.Fragment}
     */
    public abstract void onPause();

    /**
     * Should be called from {@link Activity#onResume()}
     */
    public abstract void onResume();

    /**
     * onPostCreate
     * @param savedInstanceState
     */
    public void onPostCreate(Bundle savedInstanceState){};


    /**
     * @param activity
     * @param configSetting
     * @return
     */
    public static ConfigDelegate create(Activity activity, ConfigSettingInterface configSetting) {
        return new ConfigActivityDeleagetImpl(activity, configSetting);
    }

    public static ConfigDelegate create(Fragment fragment, ConfigSettingInterface configSetting) {
        return new ConfigFragmentDeleagetImpl(fragment, configSetting);
    }
}
