package com.mobi.sdk.overseasad;

import android.content.Context;

import com.mobi.sdk.overseasad.banner.MobiBannerAdImpl;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;
import com.mobi.sdk.overseasad.network.HttpClient;
import com.mobi.sdk.overseasad.network.NetworkClient;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 15:27
 * @Dec 略
 */
public class MobiLoaderImpl implements MobiAdLoader {
    private final NetworkClient mNetworkClient;
    private Context mContext;

    public MobiLoaderImpl(Context context) {
        mContext = context.getApplicationContext();
        mNetworkClient = new NetworkClient();
    }

    @Override
    public void loadBannerAd(MobiAdRequest request, MobiCallback.BannerAdLoadCallback callback) {
        MobiBannerAdImpl mobiBannerAd = new MobiBannerAdImpl(mContext);
        mobiBannerAd.setAdLoaderCallback(callback);

        mNetworkClient.requestBannerAd(request.getPosid(), new NetworkClient.RequestCallback() {
            @Override
            public void onSuccess(Object configBean) {

            }

            @Override
            public void onFailure(int code, String message) {

            }
        });
    }


}
