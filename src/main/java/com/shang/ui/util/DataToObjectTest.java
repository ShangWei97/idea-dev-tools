package com.shang.ui.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author shangwei@tuhu.cn
 * @date 2021/1/23
 */
public class DataToObjectTest {

    /**
     * 获取key
     *
     * @param str
     * @return
     */
    private static String before(String str) {
        int i = str.indexOf("=");
        int j = i;
        if (i == -1) {
            return null;
        }
        while (true) {
            String tempStr = null;
            if (j - 1 >= 0) {
                tempStr = str.substring(j - 1, j);
            } else {
                break;
            }
            if ("()[],".indexOf(tempStr) > -1 || " ".equals(tempStr)) {
                break;
            }
            j--;
        }
        return str.substring(j, i);
    }

    /**
     * 获取value
     *
     * @param str
     * @return
     */
    private static String after(String str) {
        int i = str.indexOf("=");
        int j = i + 1;
        int length = str.length();
        if (i == -1 || j >= length) {
            return null;
        }
        while (true) {
            String tempStr1 = null;
            if (j + 1 < length) {
                tempStr1 = str.substring(j, j + 1);
            } else {
                break;
            }
            if (",".equals(tempStr1) || ")".equals(tempStr1)) {
                String tempStr2 = null;
                if (j + 2 < length) {
                    tempStr2 = str.substring(j + 1, j + 2);
                    if (" ".equals(tempStr2) || "]".equals(tempStr2) || ",".equals(tempStr2)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            j++;
        }
        return str.substring(i + 1, j);
    }

    /**
     * lombok @Data默认toString反序列化成对象
     *
     * @param str
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T strToObject(String str, Class<T> cls) {
        return strToJson(str).toJavaObject(cls);
    }

    /**
     * 数组l
     * lombok @Data默认toString反序列化成对象
     *
     * @param str
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> strToList(String str, String objStr, Class<T> cls) {
        return strToJsonArray(str, objStr).toJavaList(cls);
    }

    /**
     * 数组
     * lombok @Data默认toString反序列化成JSON
     *
     * @param str
     * @param obj
     * @return
     */

    public static JSONArray strToJsonArray(String str, String obj) {
        JSONArray array = new JSONArray();
        if ("[".equals(str.substring(0, 1)) && "]".equals(str.substring(str.length() - 1))) {
            while (true) {
                String t1 = str.replaceFirst(obj, "");
                if (t1.indexOf(obj) != -1) {
                    String temp = t1.substring(0, t1.indexOf(obj));
                    str = t1.substring(t1.indexOf(obj));
                    array.add(strToJson(temp));
                } else {
                    array.add(strToJson(t1));
                    break;
                }
            }
        } else {
            array.add(strToJson(str));
        }
        return array;
    }

    /**
     * lombok @Data默认toString反序列化成JSON
     *
     * @param str
     * @return
     */
    public static JSONObject strToJson(String str) {
        JSONObject result = new JSONObject();
        while (true) {
            String before = before(str);
            String after = after(str);
            if (before == null) {
                break;
            }
            if ("null".equals(after)) {
                result.put(before, null);
            } else {
                result.put(before, after);
            }
            str = str.replace(before + "=" + after, "");
        }
        return result;
    }


}
