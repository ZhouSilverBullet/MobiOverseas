package com.mobi.sdk.overseasad.webview.webviewclient;

import android.webkit.WebView;

/**
 * WebView回调统一处理
 * 所有涉及到WebView交互的都必须实现这个callback
 */
public interface WebViewCallBack {

    void pageStarted(String url);

    void pageFinished(String url);

    boolean overrideUrlLoading(WebView view, String url);

    void onError();
}
