package com.mobi.sdk.overseasad.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 17:15
 * @Dec 略
 */
public class BannerWebView extends BaseWebView {

    public BannerWebView(Context context) {
        this(context, null);
    }

    public BannerWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                onWebViewProgress(view, newProgress);
            }
        });
    }

    public void onWebViewProgress(WebView view, int newProgress) {
        Log.e("MobiBannerView", " onWebViewProgress newProgress -》 " + newProgress);
    }
}
