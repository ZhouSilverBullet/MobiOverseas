package com.mobi.sdk.overseasad;

import android.content.Context;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 15:44
 * @Dec 对外的使用的类
 */
public class MobiSdk {
    /**
     * 初始化，传一些必要的参数
     *
     * @param context
     * @param appId
     */
    public static void init(Context context, String appId) {
        OverseasAdSession.get().init(context.getApplicationContext(), appId);
    }

    /**
     * 初始化，传一些必要的参数
     *
     * @param context
     */
    public static void init(Context context) {
        OverseasAdSession.get().init(context.getApplicationContext(), "");
    }

    public static MobiAdLoader createBanner(Context context) {
        MobiAdLoader mobiLoader = new MobiLoaderImpl(context);
        return mobiLoader;
    }
}
