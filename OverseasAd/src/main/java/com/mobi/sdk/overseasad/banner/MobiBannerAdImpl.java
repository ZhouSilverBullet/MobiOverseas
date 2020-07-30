package com.mobi.sdk.overseasad.banner;

import android.content.Context;

import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 16:08
 * @Dec Banner广告
 */
public class MobiBannerAdImpl implements MobiBannerAd {

    private Context mContext;
    private MobiCallback.BannerAdLoadCallback mCallback;

    public MobiBannerAdImpl(Context context) {
        mContext = context.getApplicationContext();
    }

    public void setAdLoaderCallback(MobiCallback.BannerAdLoadCallback callback) {
        mCallback = callback;
    }
}
