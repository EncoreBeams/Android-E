package cn.encore.framecommon.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by：Encore
 * Created Time：16/7/2 10:50
 */
public class CloseUtils {

    /**
     * 关闭Closeable对象
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
