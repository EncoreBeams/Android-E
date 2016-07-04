package cn.encore.framecommon.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 异常捕获
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler mCrashHandler;
    private UncaughtExceptionHandler mDefaultHandler;
    private Context context;
    private SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private String logPath = "/";
    private String fileName = "log.txt";

    public static synchronized CrashHandler getInstance() {
        if (mCrashHandler != null) {
            return mCrashHandler;
        } else {
            mCrashHandler = new CrashHandler();
            return mCrashHandler;
        }
    }

    public void init(Context context) {
        this.context = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);

        if (sdCardExist) {
            //获取本地cache 目录
            logPath = context.getExternalCacheDir() + "crash" + File.separator;
        } else {
            logPath = context.getFilesDir().getAbsolutePath() + "crash" + File.separator;
        }
    }

    public void uncaughtException(Thread thread, Throwable ex) {

        handleException(ex);
        mDefaultHandler.uncaughtException(thread, ex);
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        saveLog(ex);
        return true;
    }

    private void saveLog(Throwable ex) {
        String versioninfo = getVersionInfo();
        String mobileInfo = getMobileInfo();
        String errorinfo = getErrorInfo(ex);
        try {
            boolean openSuccess = false;
            File dir = new File(logPath);

            if (!dir.exists())
                dir.mkdirs();

            File logFile = new File(logPath + fileName);
            if (!logFile.exists()) {
                openSuccess = logFile.createNewFile();
                logFile.setWritable(true);
            } else {
                openSuccess = true;
            }
            if (openSuccess) {

                OutputStream outstream = new FileOutputStream(logFile, true);
                OutputStreamWriter out = new OutputStreamWriter(outstream);
                out.append("\n\n\n" + dataFormat.format(new Date()) + ":====the app crash!!!====\n");
                out.append("versioninfo:" + versioninfo + "\n");
                out.append("mobileInfo:" + mobileInfo + "\n");
                out.append("errorinfo:" + errorinfo);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getErrorInfo(Throwable t) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        t.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }

    private String getMobileInfo() {
        StringBuffer sb = new StringBuffer();
        try {
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(null).toString();
                sb.append(name + "=" + value);
                sb.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getVersionInfo() {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }
}
