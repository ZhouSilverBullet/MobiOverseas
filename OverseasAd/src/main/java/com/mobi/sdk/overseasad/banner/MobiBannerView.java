package com.mobi.sdk.overseasad.banner;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobi.sdk.overseasad.bean.AdBean;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;
import com.mobi.sdk.overseasad.utils.Base64Utils;
import com.mobi.sdk.overseasad.utils.ResourceUtil;

import java.util.List;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 17:00
 * @Dec 略
 */
public class MobiBannerView extends FrameLayout {
    public static final String TAG = "MobiBannerView";

    public static final String webData = "<div>\n" +
            "    <div class=\"container\" style=\"width:100%; height:100%;overflow:hidden;\">你好\n" +
            "        <img alt=\"\"\n" +
            "             src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596025477191&di=899382f86868dd8ad41ddee7c1e7e99f&imgtype=0&src=http%3A%2F%2Fpic23.nipic.com%2F20120914%2F10923787_155337671108_2.gif\"/>\n" +
            "    </div>\n" +
            "    <script type=\"text/javascript\">\n" +
            "        (function(window,document){var getAllA=document.getElementsByClassName('container')[0].childNodes;for(var i=getAllA.length-1;i>=0;i--){getAllA[i].addEventListener('touchstart',function(ev){var oImg=new Image();oImg.src=\"\"},false)}})(Function('return this')(),document);\n" +
            "    \n" +
            "    </script>\n" +
            "</div>";

    private WebView mWebView;
    private MobiCallback.BannerAdLoadCallback mCallback;
    private MobiBannerAd.AdListener mListener;
    private MobiBannerAdImpl mMobiBannerAd;

    public MobiBannerView(@NonNull Context context) {
        this(context, null);
    }

    public MobiBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MobiBannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        LayoutInflater.from(getContext())
                .inflate(ResourceUtil.getIdentifierLayout("mobi_banner_view"), this);

        mWebView = findViewById(ResourceUtil.getIdentifierId("mobiWebView"));
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setLoadWithOverviewMode(true);
//        mMobiWebView.loadDataWithBaseURL(null, webData, "text/html", "utf-8", null);

        mWebView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMobiBannerAd != null) {
                    AdBean adData = mMobiBannerAd.getAdData();
                    if (adData != null) {
                        List<String> clkTrack = adData.getClkTrack();
                    }
                }
                if (mListener != null) {
                    mListener.onAdShow(MobiBannerView.this, 0);
                }
            }
        });
    }

    public void setAdLoadCallback(MobiCallback.BannerAdLoadCallback callback) {
        mCallback = callback;
    }

    public void setAdBannerListener(MobiBannerAd.AdListener listener) {
        mListener = listener;
    }

    public void render() {
        String decodeAdHtml = null;
        if (mMobiBannerAd.getAdData() != null) {
            if (mMobiBannerAd.getAdData().getStyle() == 2) {
                String ad = mMobiBannerAd.getAdData().getAd();
                decodeAdHtml = Base64Utils.decodeToString(ad);
                Log.e(TAG, "decodeAdHtml : " + decodeAdHtml);
            }
        }
        if (TextUtils.isEmpty(decodeAdHtml)) {
            //不正常显示内容

        } else {
            mWebView.loadDataWithBaseURL(null, decodeAdHtml, "text/html", "utf-8", null);
        }

        if (mListener != null) {
            mListener.onAdShow(this, 0);
        }
    }

    public void setMobiBannerAd(MobiBannerAdImpl mobiBannerAd) {
        mMobiBannerAd = mobiBannerAd;
    }
}
