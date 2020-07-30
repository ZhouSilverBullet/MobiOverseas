package com.mobi.sdk.overseasad.listener;

import android.view.View;

import com.mobi.sdk.overseasad.MobiAdRequest;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 16:02
 * @Dec 略
 */
public interface MobiBannerAd {

    /**
     * 展示的时候的接口
     */
    public interface AdListener {
        void onAdClicked(View bannerView, int index);

        void onAdShow(View bannerView, int index);
    }
}
