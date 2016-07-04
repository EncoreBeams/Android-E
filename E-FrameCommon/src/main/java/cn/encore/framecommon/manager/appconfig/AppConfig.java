package cn.encore.framecommon.manager.appconfig;

import java.io.Serializable;

/**
 * Created by：Encore
 * Created Time：16/4/20 17:23
 */
public class AppConfig implements Serializable {
    //是否 Debug 模式
    private boolean isDebug = true;
    //日志是否保存到文件
    private boolean isLogWriteToFile = false;
    //是否显示日志
    private boolean isShowLog = true;
    //线上服务器地址
    private String serverUrl;
    //测试地址
    private String debugServerUrl;

    public boolean isDebug() {
        return isDebug;
    }

    public boolean isLogWriteToFile() {
        return isLogWriteToFile;
    }

    public boolean isShowLog() {
        return isShowLog;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getDebugServerUrl() {
        return debugServerUrl;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void setLogWriteToFile(boolean logWriteToFile) {
        isLogWriteToFile = logWriteToFile;
    }

    public void setShowLog(boolean showLog) {
        isShowLog = showLog;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setDebugServerUrl(String debugServerUrl) {
        this.debugServerUrl = debugServerUrl;
    }
}
