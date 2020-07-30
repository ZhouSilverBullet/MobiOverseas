package com.mobi.sdk.overseasad.utils;

import android.content.Context;

import com.mobi.sdk.overseasad.OverseasAdSession;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 18:09
 * @Dec 略
 */
public class GaidInit {
    public static void inti(final Context context) {
        OverseasAdSession.get().getDispatcher().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdClient.AdInfo adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                    String id = adInfo.getId();
                    OverseasAdSession.get().setGaid(id);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    OverseasAdSession.get().getDispatcher().finishRunnable(this);
                }
            }
        });

    }
}
