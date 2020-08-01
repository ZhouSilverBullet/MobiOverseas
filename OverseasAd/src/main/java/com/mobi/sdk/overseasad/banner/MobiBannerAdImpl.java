package com.mobi.sdk.overseasad.banner;

import android.content.Context;
import android.view.View;

import com.mobi.sdk.overseasad.bean.AdBean;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;
import com.mobi.sdk.overseasad.network.NetworkClient;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 16:08
 * @Dec Banner广告
 */
public class MobiBannerAdImpl implements MobiBannerAd {

    private MobiBannerInnerView mBannerView;
    private Context mContext;
    private MobiCallback.BannerAdLoadCallback mCallback;
    private AdBean mAdData;
    private AdListener mListener;

    /**
     * 用于一次广告上传一次
     */
    private boolean mClickFinish;
    private boolean mShowFinish;

    public MobiBannerAdImpl(Context context) {
        mContext = context.getApplicationContext();
    }

    public void setAdLoadCallback(MobiCallback.BannerAdLoadCallback callback) {
        mCallback = callback;
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


    /**
     * 点击上报
     */
    public void reportClick() {
        if (mClickFinish) {
            return;
        }

        if (mAdData != null) {
            mClickFinish = true;
            for (String s : mAdData.getClkTrack()) {
                NetworkClient.reportAd(s);
            }
        }
    }

    /**
     * 展示上报
     */
    public void reportShow() {
        if (mShowFinish) {
            return;
        }
        if (mAdData != null) {
            mShowFinish = true;
            for (String s : mAdData.getImgTrack()) {
                NetworkClient.reportAd(s);
            }
        }
    }

    public void createBannerView() {
        mBannerView = new MobiBannerInnerView(mContext);
        if (mAdData != null && mAdData.getWidth() > 0 && mAdData.getHeight() > 0) {
            mBannerView.setBackEndSize(mAdData.getWidth(), mAdData.getHeight());
        } else {
            mBannerView.setBackEndSize(0, 0);
        }
        mBannerView.setAdLoadCallback(mCallback);
        mBannerView.setMobiBannerAd(this);
    }
}
