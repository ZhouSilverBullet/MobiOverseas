package com.mobi.sdk.overseasad.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 15:50
 * @Dec 略
 */
public class AdBeanUtil {
    public static List<AdBean> parseData(JSONArray jsonArray) {
        List<AdBean> list = new ArrayList<>();
        if (jsonArray == null || jsonArray.length() == 0) {
            return list;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject adJsonObject = jsonArray.optJSONObject(i);
            JSONObject tactJsonObject = adJsonObject.optJSONObject("tact");
            TactBean bean = getTactBean(tactJsonObject);
            List<String> imgTrack = getListString(adJsonObject.optJSONArray("img_track"));
            List<String> clkTrack = getListString(adJsonObject.optJSONArray("clk_track"));

            list.add(new AdBean(adJsonObject.optInt("style"),
                    adJsonObject.optInt("width"),
                    adJsonObject.optInt("height"),
                    adJsonObject.optInt("bdt"),
                    adJsonObject.optInt("fw"),
                    adJsonObject.optInt("ctype"),
                    adJsonObject.optString("adid"),
                    adJsonObject.optString("ad"),
                    bean, imgTrack, clkTrack
            ));
        }
        return list;
    }

    private static TactBean getTactBean(JSONObject tactJsonObject) {
        if (tactJsonObject == null) {
            return null;
        }

        return new TactBean(tactJsonObject.optInt("pcache"),
                tactJsonObject.optInt("freq_time"),
                tactJsonObject.optInt("freq_count"));
    }

    private static List<String> getListString(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        if (jsonArray == null || jsonArray.length() == 0) {
            return list;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.optString(i));
        }
        return list;
    }
}
