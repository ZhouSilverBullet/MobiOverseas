package com.mobi.sdk.overseasad.listener;

import android.view.View;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 16:02
 * @Dec 略
 */
public interface MobiBannerAd {

    /**
     * 获取BannerView进行展示
     *
     * @return
     */
    View getBannerView();

    void setAdBannerListener(AdListener listener);

    void render();

    /**
     * 展示的时候的接口
     */
    public interface AdListener {
        void onBannerClicked(View bannerView, int index);

        void onBannerExpanded(View bannerView, int index);
    }
}
