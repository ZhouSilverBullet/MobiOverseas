package com.mobi.sdk.overseasad.banner;

import android.content.Context;
import android.view.View;

import com.mobi.sdk.overseasad.bean.AdBean;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 16:08
 * @Dec Banner广告
 */
public class MobiBannerAdImpl implements MobiBannerAd {

    private final MobiBannerView mBannerView;
    private Context mContext;
    private MobiCallback.BannerAdLoadCallback mCallback;
    private AdBean mAdData;
    private AdListener mListener;

    public MobiBannerAdImpl(Context context) {
        mContext = context.getApplicationContext();
        mBannerView = new MobiBannerView(context);
        mBannerView.setMobiBannerAd(this);
    }

    public void setAdLoadCallback(MobiCallback.BannerAdLoadCallback callback) {
        mCallback = callback;
        mBannerView.setAdLoadCallback(callback);
    }

    public void setAdData(AdBean adData) {
        mAdData = adData;
    }

    public AdBean getAdData() {
        return mAdData;
    }

    @Override
    public void setAdBannerListener(AdListener listener) {
        mListener = listener;
        mBannerView.setAdBannerListener(listener);
    }

    @Override
    public void render() {
        if (mBannerView != null) {
            mBannerView.render();
        }
    }

    @Override
    public View getBannerView() {
        return mBannerView;
    }


}
