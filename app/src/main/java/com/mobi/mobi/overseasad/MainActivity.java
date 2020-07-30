package com.mobi.mobi.overseasad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.mobi.sdk.overseasad.MobiAdLoader;
import com.mobi.sdk.overseasad.MobiAdRequest;
import com.mobi.sdk.overseasad.MobiSdk;
import com.mobi.sdk.overseasad.banner.MobiBannerView;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobiSdk.init(this, "afdafdas");
        setContentView(R.layout.activity_main);

        mFrameLayout = findViewById(R.id.flLayout);

        MobiAdLoader adLoader = MobiSdk.createBanner(this);
        MobiAdRequest request = new MobiAdRequest.Builder().setPosid("11111").build();
        adLoader.loadBannerAd(request, new MobiCallback.BannerAdLoadCallback() {
            @Override
            public void onBannerAdLoad(MobiBannerAd bannerAd) {
                bannerAd.setAdBannerListener(new MobiBannerAd.AdListener() {
                    @Override
                    public void onAdClicked(View bannerView, int index) {

                    }

                    @Override
                    public void onAdShow(View bannerView, int index) {
                        mFrameLayout.addView(bannerView);
                    }
                });

                bannerAd.render();


            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }
}