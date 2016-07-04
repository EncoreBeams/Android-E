package cn.encore.framecommon.manager.appconfig;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.encore.framecommon.log.jlog.JLog;
import cn.encore.framecommon.utils.CloseUtils;
import cn.encore.framecommon.utils.JsonUtils;

/**
 * desc：APP配置信息管理器
 * Created by：Encore
 * Created Time：16/2/29 16:30
 */
public class AppConfigManager {
    private static final String TAG = "AppConfigManager";
    //build config
    private static AppConfigManager mInstance;

    private AppConfig mAppConfig;

    /**
     * 是静态内部类，该内部类的实例与外部类的实例没有绑定关系，
     * 而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        //保证了线程安全
        private static final AppConfigManager INSTANCE = new AppConfigManager();
    }

    public static final AppConfigManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取编译信息
     *
     * @param context
     * @return
     */
    public AppConfig getAppConfig(Context context) {
        JLog.d(TAG, "getAppConfig()");
        if (mAppConfig != null) {
            return mAppConfig;
        }
        String config = readConfig(context);
        if (!config.equals("")) {
            mAppConfig = JsonUtils.shareJsonUtils().parseJson2Obj(config, AppConfig.class);
        }

        if (mAppConfig == null) {
            mAppConfig = new AppConfig();
        }
        return mAppConfig;
    }


    /**
     * 读取配置文件
     *
     * @param context
     * @return
     */
    private String readConfig(Context context) {
        JLog.d(TAG, "read App Config");
        InputStream is = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        StringBuffer buffer = new StringBuffer("");
        try {
            is = context.getAssets().open("build.config");
            reader = new InputStreamReader(is);
            bufferedReader = new BufferedReader(reader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
                buffer.append("\n");
            }
        } catch (IOException e) {
            JLog.e(TAG, "read app config exception!");
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(is);
            CloseUtils.closeQuietly(reader);
            CloseUtils.closeQuietly(bufferedReader);
        }
        return buffer.toString();
    }
}
