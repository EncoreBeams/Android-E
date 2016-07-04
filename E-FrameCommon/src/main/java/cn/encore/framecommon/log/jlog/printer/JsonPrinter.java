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

package cn.encore.framecommon.log.jlog.printer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.encore.framecommon.log.jlog.constant.LogLevel;
import cn.encore.framecommon.log.jlog.util.PrinterUtils;

/**
 * JSON打印机.
 */
public class JsonPrinter implements Printer {

    /** JSON的缩进量. */
    private static final int JSON_INDENT = 4;

    @Override
    public void printConsole(LogLevel level, String tag, String message, StackTraceElement element) {
        String json;
        try {
            if (message.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(message);
                json = jsonObject.toString(JSON_INDENT);
            } else if (message.startsWith("{")) {
                JSONArray jsonArray = new JSONArray(message);
                json = jsonArray.toString(JSON_INDENT);
            } else {
                json = message;
            }
        } catch (JSONException e) {
            json = message;
        }
        PrinterUtils.printConsole(level, tag, PrinterUtils.decorateMsgForConsole(json, element));
    }

    @Override
    public void printFile(LogLevel level, String tag, String message, StackTraceElement element) {
        synchronized(Printer.class) {
            PrinterUtils.printFile(PrinterUtils.decorateMsgForFile(level, message, element));
        }
    }
}