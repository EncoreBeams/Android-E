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

package cn.encore.framecommon.log.jlog;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import cn.encore.framecommon.log.jlog.constant.LogLevel;
import cn.encore.framecommon.log.jlog.constant.LogSegment;
import cn.encore.framecommon.log.jlog.constant.ZoneOffset;

/**
 * 配置.
 */
public class Settings {

    private Context mContext;
    /** DEBUG模式. */
    private boolean mIsDebug;
    /** 字符集. */
    private String mCharset;
    /** 时间格式. */
    private String mTimeFormat;
    /** 时区偏移时间. */
    private ZoneOffset mZoneOffset;
    /** 日志保存的目录. */
    private String mLogDir;
    /** 日志文件的前缀. */
    private String mLogPrefix;
    /** 切片间隔，单位小时. */
    private LogSegment mLogSegment;
    /** 日志是否记录到文件中. */
    private boolean mWriteToFile;
    /** 写入文件的日志级别. */
    private List<LogLevel> mLogLevelsForFile;
    /** 封装的层级，V、D、I、W、E、WTF、JSON共用，请确保他们封装在同一层级中. */
    private int mPackagedLevel;

    public Settings() {
        mIsDebug = true;
        mCharset = "UTF-8";
        mTimeFormat = "yyyy-MM-dd HH:mm:ss";
        mZoneOffset = ZoneOffset.P0800;
        mLogDir = "jlog";
        mLogPrefix = "";
        mLogSegment = LogSegment.TWENTY_FOUR_HOURS;
        mWriteToFile = false;
        mLogLevelsForFile = new ArrayList<>();
        mLogLevelsForFile.add(LogLevel.ERROR);
        mLogLevelsForFile.add(LogLevel.WTF);
        mPackagedLevel = 0;
    }

    public Context getContext() {
        return mContext;
    }

    public Settings setContext(@NonNull Context context) {
        mContext = context;
        return this;
    }

    public String getCharset() {
        return mCharset;
    }

    public Settings setCharset(@NonNull String charset) {
        mCharset = charset;
        return this;
    }

    public String getTimeFormat() {
        return mTimeFormat;
    }

    public Settings setTimeFormat(@NonNull String timeFormat) {
        mTimeFormat = timeFormat;
        return this;
    }

    public ZoneOffset getZoneOffset() {
        return mZoneOffset;
    }

    public Settings setZoneOffset(@NonNull ZoneOffset zoneOffset) {
        mZoneOffset = zoneOffset;
        return this;
    }

    public String getLogDir() {
        return mLogDir;
    }

    public Settings setLogDir(@NonNull String logDir) {
        mLogDir = logDir;
        return this;
    }

    public String getLogPrefix() {
        return mLogPrefix;
    }

    public Settings setLogPrefix(@NonNull String logPrefix) {
        mLogPrefix = logPrefix;
        return this;
    }

    public LogSegment getLogSegment() {
        return mLogSegment;
    }

    public Settings setLogSegment(@NonNull LogSegment logSegment) {
        mLogSegment = logSegment;
        return this;
    }

    public boolean isWriteToFile() {
        return mWriteToFile;
    }

    public Settings writeToFile(boolean isWriteToFile) {
        mWriteToFile = isWriteToFile;
        return this;
    }

    public List<LogLevel> getLogLevelsForFile() {
        return mLogLevelsForFile;
    }

    public Settings setLogLevelsForFile(@NonNull List<LogLevel> logLevelsForFile) {
        mLogLevelsForFile = logLevelsForFile;
        return this;
    }

    public boolean isDebug() {
        return mIsDebug;
    }

    public Settings setDebug(boolean isDebug) {
        mIsDebug = isDebug;
        return this;
    }

    public int getPackagedLevel() {
        return mPackagedLevel;
    }

    public Settings setPackagedLevel(int packagedLevel) {
        mPackagedLevel = packagedLevel;
        return this;
    }
}