package com.mobi.sdk.overseasad.webview.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.mobi.sdk.overseasad.network.NetworkClient;

import java.util.List;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/8/1 15:52
 * @Dec 略
 */
public enum UrlAction {
    NATIVE_BROWSER(true) {
        @Override
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "mopubnativebrowser".equalsIgnoreCase(uri.getScheme()) ||
                    "mobnativebrowser".equalsIgnoreCase(uri.getScheme());
        }

        @Override
        protected void performAction(@NonNull Context context, @NonNull Uri uri) {
            if ("navigate".equals(uri.getHost())) {
                String queryUrl = uri.getQueryParameter("url");
                if (!TextUtils.isEmpty(queryUrl)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        }
    },

    FOLLOW_DEEP_LINK_WITH_FALLBACK(true) {
        @Override
        public boolean shouldTryHandlingUrl(@NonNull Uri uri) {
            return "deeplink+".equals(uri.getScheme());
        }

        @Override
        protected void performAction(@NonNull final Context context, @NonNull final Uri uri) {
            String primaryUrl = null;
            List<String> primaryTrackingUrls = null;
            String fallbackUrl = null;
            List<String> fallbackTrackingUrls = null;
            try {
                primaryUrl = uri.getQueryParameter("primaryUrl");
                primaryTrackingUrls = uri.getQueryParameters("primaryTrackingUrl");
                fallbackUrl = uri.getQueryParameter("fallbackUrl");
                fallbackTrackingUrls = uri.getQueryParameters("fallbackTrackingUrl");
            } catch (UnsupportedOperationException e) {
            }

            if (!TextUtils.isEmpty(primaryUrl)) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(primaryUrl));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    trackUrl(fallbackTrackingUrls);
                    return;
                } catch (Exception e) {
                }
            }

//            trackUrl();
        }
    };

    private final boolean mRequiresUserInteraction;

    UrlAction(boolean requiresUserInteraction) {
        mRequiresUserInteraction = requiresUserInteraction;
    }

    public abstract boolean shouldTryHandlingUrl(@NonNull final Uri uri);

    protected abstract void performAction(@NonNull final Context context, @NonNull final Uri uri);

    public void handleUrl(@NonNull final Context context, @NonNull final Uri uri) {
        performAction(context, uri);
    }

    public void trackUrl(List<String> urls) {
        if (urls == null) {
            return;
        }
        for (String url : urls) {
            NetworkClient.reportAd(url);
        }
    }
}
