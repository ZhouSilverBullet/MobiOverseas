package com.mobi.sdk.overseasad.bean;

import com.mobi.sdk.overseasad.network.Response;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 15:53
 * @Dec 略
 */
public class JsonUtil {
    public static JSONObject string2JSONObject(String json) {
        JSONObject jsonObject = null;
        try {
            JSONTokener jsonParser = new JSONTokener(json);
            jsonObject = (JSONObject) jsonParser.nextValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
