package cn.encore.framecommon.base;

import android.app.Application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.encore.framecommon.log.jlog.JLog;
import cn.encore.framecommon.log.jlog.constant.LogLevel;
import cn.encore.framecommon.manager.appconfig.AppConfig;
import cn.encore.framecommon.manager.appconfig.AppConfigManager;
import cn.encore.framecommon.utils.CrashHandler;

/**
 * Created by：Encore
 * Created Time：16/7/1 15:47
 */
public abstract class EBaseApplication extends Application {

    private final String LOG_PATH = "JLog";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化App配置
        initAppConfig();
    }

    //初始化App配置
    private void initAppConfig(){
        AppConfig appConfig = AppConfigManager.getInstance().getAppConfig(getApplicationContext());
        //init log
        initLog(appConfig);
        //收集崩溃日志
        CrashHandler.getInstance().init(getApplicationContext());
    }

    //init log
    private void initLog(AppConfig appConfig){
        if(appConfig == null){
            return;
        }

        List<LogLevel> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.JSON);
        logLevels.add(LogLevel.INFO);
        //is show log
        JLog.init(getApplicationContext())
                .writeToFile(appConfig.isLogWriteToFile())
                .setLogDir(LOG_PATH + File.separator + getPackageName())
                .setTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
                .setLogLevelsForFile(logLevels)
                .setDebug(appConfig.isShowLog());
    }
}
