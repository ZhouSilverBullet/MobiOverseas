package com.mobi.mobi.overseasad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.mobi.sdk.overseasad.MobiAdLoader;
import com.mobi.sdk.overseasad.MobiAdRequest;
import com.mobi.sdk.overseasad.MobiError;
import com.mobi.sdk.overseasad.MobiSdk;
import com.mobi.sdk.overseasad.banner.MobiBannerView;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobiSdk.init(this, "afdafdas");
        setContentView(R.layout.activity_main);

        mFrameLayout = findViewById(R.id.flLayout);

        MobiBannerView bannerView = findViewById(R.id.mobiBanner);
        bannerView.setAdListener(new MobiBannerView.BannerAdListener() {

            @Override
            public void onBannerLoaded(@NonNull View banner) {

            }

            @Override
            public void onBannerFailed(View banner, MobiError error) {

            }

            @Override
            public void onBannerClicked(View banner) {

            }

            @Override
            public void onBannerExpanded(View banner) {

            }

            @Override
            public void onBannerCollapsed(View banner) {

            }
        });
        MobiAdRequest request = new MobiAdRequest.Builder().setPosid("212001").build();
        bannerView.load(request);

//        MobiAdLoader adLoader = MobiSdk.createBanner(this);
//        adLoader.loadBannerAd(request, new MobiCallback.BannerAdLoadCallback() {
//            @Override
//            public void onBannerLoaded(MobiBannerAd bannerAd) {
//                bannerAd.setAdBannerListener(new MobiBannerAd.AdListener() {
//                    @Override
//                    public void onBannerClicked(View bannerView, int index) {
//
//                    }
//
//                    @Override
//                    public void onBannerExpanded(View bannerView, int index) {
//                        mFrameLayout.addView(bannerView);
//                    }
//                });
//
//                bannerAd.render();
//            }
//
//            @Override
//            public void onBannerFailed(int code, String message) {
//                Log.e(TAG, "code : " + code + ", message: " + message);
//            }
//        });
    }
}