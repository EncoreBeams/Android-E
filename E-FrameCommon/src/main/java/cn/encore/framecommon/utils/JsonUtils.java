package cn.encore.framecommon.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Encore.liang
 * @version V1.0
 * @date 2011-9-6
 * @description
 */
public class JsonUtils {

    private static final String TAG = "JacksonUtils";
    private static JsonUtils mJsonUtils;

    // private ObjectMapper mObjectMapper;

    public JsonUtils() {
    }

    public static JsonUtils shareJsonUtils() {

        if (mJsonUtils == null) {
            mJsonUtils = new JsonUtils();
        }
        return mJsonUtils;
    }

    /**
     * 使用： ArrayList<AccountBean> beanList = new ArrayList<AccountBean>();
     * <p/>
     * AccountBean bean = new AccountBean(); bean.setAddress("a1");
     * bean.setEmail("email1"); bean.setId(1111); bean.setName("name");
     * beanList.add(bean);
     * <p/>
     * AccountBean bean2 = new AccountBean(); bean2.setAddress("a2");
     * bean2.setEmail("email2"); bean2.setId(2222); bean2.setName("name2");
     * beanList.add(bean2);
     * <p/>
     * String aString = JacksonUtils.parseObj2Json(beanList);
     *
     * @param bean
     * @return
     * @throws
     */
    public String parseObj2Json(Object bean) {
        if (bean == null) {
            return null;
        }

        String jsonString = JSON.toJSONString(bean);

        return jsonString;
    }

    /**
     * 使用： String json = "{\"list\":[" + "{\"" + "address\": \"address2\"," +
     * "\"name\":\"haha2\"," + "\"id\":2," + "\"email\":\"email2\"" + "},"+ "{"
     * + "\"address\":\"address\"," + "\"name\":\"haha\"," + "\"id\":1," +
     * "\"email\":\"email\"" + "}" + "]}"; AccountBeanResult obj =
     * JacksonUtils.parseJson2Obj(json, AccountBeanResult.class);
     *
     * @param <T>
     * @param jsonStr
     * @param c
     * @return
     * @throws
     */
    public <T> T parseJson2Obj(String jsonStr, Class<T> c) {
        if (jsonStr == null) {
            return null;
        }

        T t = null;
        try {
            t = JSON.parseObject(jsonStr, c);
            return t;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用： String json = "[" + "{\"" + "address\": \"address2\"," +
     * "\"name\":\"haha2\"," + "\"id\":2," + "\"email\":\"email2\"" + "},"+ "{"
     * + "\"address\":\"address\"," + "\"name\":\"haha\"," + "\"id\":1," +
     * "\"email\":\"email\"" + "}" + "]"; ArrayList<AccountBean> beanList =
     * JacksonUtils.parseJson2List(json, AccountBean.class);
     *
     * @param <T>
     * @param json
     * @param c
     * @return
     * @throws
     */
    public <T> List<T> parseJson2List(String json, Class<T> c) {
        if (json == null) {
            return null;
        }

        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(json, c);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
