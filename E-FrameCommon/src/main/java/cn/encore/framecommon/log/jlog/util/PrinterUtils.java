/*
 * Copyright JiongBull 2016
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.encore.framecommon.log.jlog.util;


import android.support.annotation.NonNull;

import java.io.File;

import cn.encore.framecommon.log.jlog.constant.LogLevel;
import cn.encore.framecommon.log.jlog.printer.Printer;

/**
 * 打印机工具类.
 */
public class PrinterUtils {

    /** 控制台打印的内容格式. */
    private static final String PRINT_CONSOLE_FORMAT = "[(%1$s:%2$d)#%3$s]" + Printer.LINE_SEPARATOR + "%4$s";
    /** 文件中保存的内容格式. */
    private static final String PRINT_FILE_FORMAT = "[%1$s %2$s %3$s:%4$d]" + Printer.LINE_SEPARATOR + "%5$s"
            + Printer.LINE_SEPARATOR + Printer.LINE_SEPARATOR;

    private PrinterUtils() {
    }

    /**
     * 日志打印输出到控制台.
     *
     * @param level   级别
     * @param tag     标签
     * @param message 信息
     */
    public static void printConsole(@NonNull LogLevel level, @NonNull String tag, @NonNull String message) {
        LogUtils.log(level, tag, message);
    }

    /**
     * 日志打印输出到文件.
     *
     * @param message 信息
     */
    public static void printFile(@NonNull String message) {
        String dirPath = LogUtils.genDirPath();
        String fileName = LogUtils.genFileName();

        if (!FileUtils.isExist(dirPath + File.separator + fileName)) {
            message = SysUtils.genInfo() + message;
        }

        FileUtils.write(dirPath, fileName, message, false);
    }

    /**
     * 装饰打印到控制台的信息.
     *
     * @param message 信息
     * @param element 对战元素
     * @return 装饰后的信息
     */
    public static String decorateMsgForConsole(String message, @NonNull StackTraceElement element) {
        String methodName = element.getMethodName();
        int lineNumber = element.getLineNumber();
        String fileName = element.getFileName();
        return String.format(PRINT_CONSOLE_FORMAT, fileName, lineNumber, methodName, message);
    }

    /**
     * 装饰打印到文件的信息.
     *
     * @param level   级别
     * @param message 信息
     * @param element 堆栈元素
     * @return 装饰后的信息
     */
    public static String decorateMsgForFile(
            @NonNull LogLevel level, String message, @NonNull StackTraceElement element) {
        String time = TimeUtils.getCurTime();
        String fileName = element.getFileName();
        int lineNum = element.getLineNumber();
        return String.format(PRINT_FILE_FORMAT, time, level.getValue(), fileName, lineNum, message);
    }
}