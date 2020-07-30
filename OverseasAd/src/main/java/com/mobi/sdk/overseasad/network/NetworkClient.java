package com.mobi.sdk.overseasad.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.mobi.overseasad.BuildConfig;
import com.mobi.sdk.overseasad.MobiConstantValue;
import com.mobi.sdk.overseasad.OverseasAdSession;
import com.mobi.sdk.overseasad.bean.AdBean;
import com.mobi.sdk.overseasad.bean.AdBeanUtil;
import com.mobi.sdk.overseasad.bean.JsonUtil;
import com.mobi.sdk.overseasad.utils.DeviceUtil;
import com.mobi.sdk.overseasad.utils.NetUtil;

import org.json.JSONObject;

import java.util.List;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/6/5 14:10
 * @Dec 略
 */
public class NetworkClient {
    public static final String TAG = "NetworkControl";

    public void requestBannerAd(final String posid, final RequestCallback<List<AdBean>> callback) {
        OverseasAdSession.get().getDispatcher().execute(new Runnable() {
            @Override
            public void run() {
                try {

                    String requestUrl = getRequestUrl(MobiConstantValue.LOCAL_URL, posid);
                    Request request = new Request.Builder()
                            .setMethod(Request.GET)
                            .setUrl(requestUrl)
                            .build();

                    requestAd(request, new RequestCallback<JSONObject>() {
                        @Override
                        public void onSuccess(JSONObject data) {
                            List<AdBean> ads = AdBeanUtil.parseData(data.optJSONArray("ads"));
                            if (callback != null) {
                                callback.onSuccess(ads);
                            }
                        }

                        @Override
                        public void onFailure(int code, String message) {
                            if (callback != null) {
                                callback.onFailure(code, message);
                            }
                        }
                    });

                } finally {
                    OverseasAdSession.get().getDispatcher().finishRunnable(this);
                }
            }
        });
    }

    public void requestAd(Request request, RequestCallback<JSONObject> callback) {
        HttpClient httpClient = new HttpClient();
        Response response = httpClient.execute(request);
        if (response.getCode() == 200) {
            String resContent = response.body();
            Log.e(TAG, "resContent: " + resContent);
            JSONObject jsonObject = JsonUtil.string2JSONObject(resContent);
            int code = -100;
            String msg = "";
            if (jsonObject != null) {
                code = jsonObject.optInt("code");
                msg = jsonObject.optString("msg");
                if (code == 0) {
                    if (callback != null) {
                        callback.onSuccess(jsonObject);
                    }
                    //解析成功就返回
                    return;
                }
            }
            //code非0就属于报错
            if (callback != null) {
                callback.onFailure(code, msg);
            }
        }
    }

    /**
     * http://xxx.com/xxx/?tag=3&
     * posid=10001&
     * aid=4E5D8E5B-35D5-4445-8598-F99DD83EAB57&
     * devicetype=4&
     * ifa=143E9375-617C-49E8-87D2-BD5F9734B860&
     * lan=zh_CN&
     * mak=Apple&
     * mcc=&mnc=&
     * mod=iPhone10.3&
     * nt=2&
     * os=iOS&
     * osv=11.3.1&
     * res=2436*1125&
     * us_privacy=1---
     *
     * @param reqUrl
     * @return
     */
    private static String getRequestUrl(String reqUrl, String posid) {
        if (TextUtils.isEmpty(reqUrl)) {
            return "";
        }
        Context context = OverseasAdSession.get().getContext();

        String[] mccAndMnc = DeviceUtil.getMccAndMnc(context);
        String devicetype;
        if (DeviceUtil.isPad(context)) {
            devicetype = "5";
        } else {
            devicetype = "4";
        }
        StringBuilder sb = new StringBuilder(reqUrl);
        sb.append("?sdkv=").append(BuildConfig.VERSION_NAME);
        //tag = 3 代表sdk请求
        sb.append("&tag=").append(3);
        sb.append("&posid=").append(posid);
        //gaid google广告id
        sb.append("&ifa=").append(OverseasAdSession.get().getGaid());
        sb.append("&imei=").append(DeviceUtil.getDeviceId(context));
        sb.append("&oaid=").append(OverseasAdSession.get().getOaid());
        sb.append("&aid=").append(DeviceUtil.getAndroidId(context));
        sb.append("&mac=").append(DeviceUtil.getMacAddress(context));
        sb.append("&lan=").append(DeviceUtil.getLanguageAndCountry());
        sb.append("&mak=").append(DeviceUtil.getBrand());
        sb.append("&mod=").append(DeviceUtil.getSystemModel());
        sb.append("&mcc=").append(mccAndMnc[0]);
        sb.append("&mnc=").append(mccAndMnc[1]);
        sb.append("&devicetype=").append(devicetype);
        sb.append("&nt=").append(NetUtil.getConnectionType(context));
        sb.append("&os=").append(MobiConstantValue.PLATFORM);
        sb.append("&osv=").append(DeviceUtil.getSystemVersion());
        String[] location = DeviceUtil.getLocation(context);
        sb.append("&lat=").append(location[1]);
        sb.append("&lon=").append(location[0]);
        sb.append("&res=").append(DeviceUtil.getScreenWidth(context)).append("*").append(DeviceUtil.getScreenHeight(context));
        sb.append("&hw=").append("");
        //请求超时是多少毫秒
        sb.append("tmax=").append(500);
        sb.append("&us_privacy=").append("");

        return sb.toString();
    }


    public interface RequestCallback<T> {
        void onSuccess(T data);

        void onFailure(int code, String message);
    }


    public static void reportAd(final String url) {
        OverseasAdSession.get().getDispatcher().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .setMethod(Request.GET)
                            .setUrl(url)
                            .build();
                    HttpClient httpClient = new HttpClient();
                    //请求
                    httpClient.execute(request);
                } catch (Exception e) {

                } finally {
                    OverseasAdSession.get().getDispatcher().finishRunnable(this);
                }
            }
        });
    }
}
