package com.mobi.sdk.overseasad.banner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;
import com.mobi.sdk.overseasad.utils.Base64Utils;
import com.mobi.sdk.overseasad.utils.ResourceUtil;
import com.mobi.sdk.overseasad.webview.BannerWebView;
import com.mobi.sdk.overseasad.webview.webviewclient.WebViewCallBack;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 17:00
 * @Dec 略
 */
public class MobiBannerView extends FrameLayout implements WebViewCallBack {

    public static final String TAG = "MobiBannerView";

    private BannerWebView mWebView;
    private MobiCallback.BannerAdLoadCallback mCallback;
    private MobiBannerAd.AdListener mListener;
    private MobiBannerAdImpl mMobiBannerAd;

    //服务端给的宽和高
    private int mBackEndWidth;
    private int mBackEndHeight;

    public MobiBannerView(@NonNull Context context) {
        this(context, null);
    }

    public MobiBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MobiBannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, getMeasuredWidth() + ", " + getMeasuredHeight());
    }

    public void setMobiBannerAd(MobiBannerAdImpl mobiBannerAd) {
        mMobiBannerAd = mobiBannerAd;
    }

    @Override
    public void pageStarted(String url) {
        Log.e(TAG, " pageStarted ");
    }

    @Override
    public void pageFinished(String url) {
        Log.e(TAG, " pageFinished ");

        //网页加载完成，上报show事件
        if (mMobiBannerAd != null) {
            mMobiBannerAd.reportShow();
        }
    }

    @Override
    public boolean overrideUrlLoading(WebView view, String url) {
        Log.e(TAG, " overrideUrlLoading ");
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http")) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            } else {
                //跳转googlePlay
                launchGooglePlayAppDetail(view.getContext(), url);
            }
        }

        if (mListener != null) {
            mListener.onAdClicked(MobiBannerView.this, 0);
        }

        if (mMobiBannerAd != null) {
            mMobiBannerAd.reportClick();
        }

        return true;
    }

    private void launchGooglePlayAppDetail(Context context, String url) {    //appPkg 是应用的包名
        final String GOOGLE_PLAY = "com.android.vending";//这里对应的是谷歌商店，跳转别的商店改成对应的即可
        try {
            if (TextUtils.isEmpty(url))
                return;
//            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(GOOGLE_PLAY);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "跳转失败");
            if (mCallback != null) {
                mCallback.onError(10003, "skip error");
            }
        }
    }

    @Override
    public void onError() {
        Log.e(TAG, " onError ");
        if (mCallback != null) {
            mCallback.onError(10002, "page loading error");
        }
    }

    public void setBackEndSize(int width, int height) {
        View view = LayoutInflater.from(getContext())
                .inflate(ResourceUtil.getIdentifierLayout("mobi_banner_view"), null);
        if (width == 0 || height == 0) {
            addView(view);
        } else {
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            float ratio = width / (widthPixels * 1f);

            this.mBackEndWidth = widthPixels;
            this.mBackEndHeight = (int) (height / ratio);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(mBackEndWidth, mBackEndHeight);
            addView(view, lp);
        }

        mWebView = findViewById(ResourceUtil.getIdentifierId("mobiWebView"));
        mWebView.registerWebViewCallBack(this);
    }
}
