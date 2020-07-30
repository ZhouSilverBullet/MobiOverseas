package com.mobi.sdk.overseasad.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobi.sdk.overseasad.utils.ResourceUtil;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 17:00
 * @Dec 略
 */
public class MobiBannerView extends FrameLayout {

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
    }

    public void load() {

    }

}
