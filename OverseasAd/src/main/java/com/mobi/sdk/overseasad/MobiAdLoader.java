package com.mobi.sdk.overseasad;

import com.mobi.sdk.overseasad.listener.MobiCallback;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 15:25
 * @Dec 略
 */
public interface MobiAdLoader {
    /**
     * 加载广告
     * @param request
     * @param callback
     */
    void loadBannerAd(MobiAdRequest request, MobiCallback.BannerAdLoadCallback callback);
}
