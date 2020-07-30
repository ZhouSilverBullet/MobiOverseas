package com.mobi.mobi.overseasad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mobi.sdk.overseasad.MobiAdLoader;
import com.mobi.sdk.overseasad.MobiAdRequest;
import com.mobi.sdk.overseasad.MobiSdk;
import com.mobi.sdk.overseasad.banner.MobiBannerView;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;

public class MainActivity extends AppCompatActivity {

    private MobiBannerView bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobiSdk.init(this, "afdafdas");
        setContentView(R.layout.activity_main);

        bannerView = findViewById(R.id.mobiBanner);

        MobiAdLoader adLoader = MobiSdk.createBanner(this);
        MobiAdRequest request = new MobiAdRequest.Builder().setPosid("11111").build();
        adLoader.loadBannerAd(request, new MobiCallback.BannerAdLoadCallback() {
            @Override
            public void onBannerAdLoad(MobiBannerAd bannerAd) {

            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }
}