package com.mobi.sdk.overseasad.network;

import android.content.Context;

import com.mobi.overseasad.BuildConfig;
import com.mobi.sdk.overseasad.OverseasAdSession;
import com.mobi.sdk.overseasad.utils.DeviceUtil;
import com.mobi.sdk.overseasad.utils.NetUtil;

import java.util.Map;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/6/18 21:17
 * @Dec
 * http://xxx.com/xxx/?tag=3&
 * posid=10001&
 * aid=4E5D8E5B-35D5-4445-8598-F99DD83EAB57&
 * devicetype=4&
 * ifa=143E9375-617C-49E8-87D2-BD5F9734B860&
 * lan=zh_CN&
 * mak=Apple&mcc=&
 * mnc=&
 * mod=iPhone10.3&
 * nt=2&
 * os=iOS&
 * osv=11.3.1&
 * res=2436*1125&
 * us_privacy=1---
 */
public class RequestUtil {


    /**
     * 上报的头部数据
     */
    public static void putEventHeader(Request request) {
        Context context = OverseasAdSession.get().getContext();
        if (context == null) {
            return;
        }

        Map<String, String> headers = request.getHeaders();
        headers.put("crr", DeviceUtil.getCellularOperator(context));
        headers.put("uud", DeviceUtil.getAndroidId(context));
        headers.put("med", DeviceUtil.getDeviceId(context));
        headers.put("fad", "");
//        headers.put("oad", CoreSession.get().getOaId());
        headers.put("mk", DeviceUtil.getBrand());
        headers.put("md", DeviceUtil.getSystemModel());
        headers.put("osv", DeviceUtil.getSystemVersion());
        headers.put("os", "android");
        headers.put("lan", DeviceUtil.getLanguageAndCountry());
        headers.put("ver", BuildConfig.VERSION_NAME);
        String[] location = DeviceUtil.getLocation(context);
        headers.put("lon", location[0]);
        headers.put("lat", location[1]);
        headers.put("nt", String.valueOf(NetUtil.getConnectionType(context)));

    }


}
