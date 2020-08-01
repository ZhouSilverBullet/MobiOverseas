package com.mobi.sdk.overseasad.webview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

import com.mobi.sdk.overseasad.webview.settings.WebviewDefaultSetting;
import com.mobi.sdk.overseasad.webview.webviewclient.MobFinishCallback;
import com.mobi.sdk.overseasad.webview.webviewclient.MobiWebviewClient;
import com.mobi.sdk.overseasad.webview.webviewclient.WebViewCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 17:06
 * @Dec 略
 */
public class BaseWebView extends WebView implements MobiWebviewClient.WebviewTouch {
    public static final String TAG = "BaseWebView";

    private WebViewCallBack webViewCallBack;
    private MobiWebviewClient mClient;
    private Map<String, String> mHeaders;

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

    public void setHeaders(Map<String, String> mHeaders) {
        this.mHeaders = mHeaders;
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        this.webViewCallBack = webViewCallBack;
        if (mClient != null) {
            mClient.setWebViewCallBack(webViewCallBack);
        }
    }

    public void setMobFinishCallback(MobFinishCallback mobFinishCallback) {
        if (mClient != null) {
            mClient.setMobFinishCallback(mobFinishCallback);
        }
    }



    @Override
    public void loadUrl(String url) {
        if (mHeaders == null) {
            super.loadUrl(url);
        } else {
            super.loadUrl(url, mHeaders);
        }
        Log.e(TAG, "DWebView load url: " + url);
        resetAllStateInternal(url);
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        super.loadUrl(url, additionalHttpHeaders);
        Log.e(TAG, "DWebView load url: " + url);
        resetAllStateInternal(url);
    }

    private boolean mTouchByUser;

    @Override
    public boolean isTouchByUser() {
        return mTouchByUser;
    }

    private void resetAllStateInternal(String url) {
        if (!TextUtils.isEmpty(url) && url.startsWith("javascript:")) {
            return;
        }
        resetAllState();
    }

    // 加载url时重置touch状态
    protected void resetAllState() {
        mTouchByUser = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchByUser = true;
                break;
        }
        return super.onTouchEvent(event);
    }
}
