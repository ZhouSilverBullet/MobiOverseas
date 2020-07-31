package com.mobi.sdk.overseasad;

import android.content.Context;

import com.mobi.sdk.overseasad.banner.MobiBannerAdImpl;
import com.mobi.sdk.overseasad.bean.AdBean;
import com.mobi.sdk.overseasad.listener.MobiCallback;
import com.mobi.sdk.overseasad.network.NetworkClient;

import java.util.List;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 15:27
 * @Dec 略
 */
public class MobiLoaderImpl implements MobiAdLoader {
    private final NetworkClient mNetworkClient;
    private Context mContext;
    private MobiBannerAdImpl mMobiBannerAd;

    public MobiLoaderImpl(Context context) {
        mContext = context.getApplicationContext();
        mNetworkClient = new NetworkClient();
    }

    @Override
    public void loadBannerAd(MobiAdRequest request, final MobiCallback.BannerAdLoadCallback callback) {
        mMobiBannerAd = new MobiBannerAdImpl(mContext);
        mMobiBannerAd.setAdLoadCallback(callback);

        mNetworkClient.requestBannerAd(request.getPosid(), new NetworkClient.RequestCallback<List<AdBean>>() {
            @Override
            public void onSuccess(final List<AdBean> data) {
                if (mMobiBannerAd != null) {
                    for (AdBean datum : data) {
                        if (datum.getStyle() == 2) {
                            mMobiBannerAd.setAdData(datum);
                            break;
                        }
                    }

                    OverseasAdSession.get().runUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMobiBannerAd.createBannerView();
                            if (mMobiBannerAd.getAdData() == null) {
                                if (callback != null) {
                                    callback.onError(10001, "data is empty");
                                }
                            } else {
                                if (callback != null) {
                                    callback.onBannerAdLoad(mMobiBannerAd);
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(int code, String message) {
                if (callback != null) {
                    callback.onError(code, message);
                }
            }
        });
    }


}
