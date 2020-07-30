package com.mobi.sdk.overseasad.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.mobi.sdk.overseasad.webview.settings.WebviewDefaultSetting;
import com.mobi.sdk.overseasad.webview.webviewclient.MobiWebviewClient;
import com.mobi.sdk.overseasad.webview.webviewclient.WebViewCallBack;

import java.util.HashMap;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 17:06
 * @Dec 略
 */
public class BaseWebView extends WebView implements MobiWebviewClient.WebviewTouch {

    private WebViewCallBack webViewCallBack;
    private MobiWebviewClient mClient;

    public BaseWebView(Context context) {
        this(context, null);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        WebviewDefaultSetting.getInstance().toSetting(this);
        mClient = new MobiWebviewClient(this, new HashMap<String, String>(), this);
        setWebViewClient(mClient);
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        this.webViewCallBack = webViewCallBack;
        if (mClient != null) {
            mClient.setWebViewCallBack(webViewCallBack);
        }
    }

    @Override
    public boolean isTouchByUser() {
        return true;
    }
}
