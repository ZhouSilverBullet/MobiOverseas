package com.mobi.sdk.overseasad.listener;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 15:52
 * @Dec 略
 */
public interface MobiCallback {

    public interface BannerAdLoadCallback {
        /**
         * 广告下载
         * @param bannerAd
         */
        void onBannerLoaded(MobiBannerAd bannerAd);

        /**
         * 错误消息
         * @param code
         * @param message
         */
        void onBannerFailed(int code, String message);
    }

}
