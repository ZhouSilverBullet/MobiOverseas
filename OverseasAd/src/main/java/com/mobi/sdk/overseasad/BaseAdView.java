package com.mobi.sdk.overseasad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/31 19:45
 * @Dec 略
 */
public class BaseAdView extends FrameLayout {
    public BaseAdView(Context context) {
        this(context, null);
    }

    public BaseAdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseAdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//
//    }

//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        View view;
//        if ((view = this.getChildAt(0)) != null && view.getVisibility() != View.GONE) {
//            int measuredWidth = view.getMeasuredWidth();
//            int measuredHeight = view.getMeasuredHeight();
//            int width = (r - l - measuredWidth) / 2;
//            int height = (b - t - measuredHeight) / 2;
//            view.layout(width, height, width + measuredWidth, height + measuredHeight);
//        }
//    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

//    protected void onMeasure(int var1, int var2) {
//        int var3 = 0;
//        int var4 = 0;
//        View var5;
//        if ((var5 = this.getChildAt(0)) != null && var5.getVisibility() != 8) {
//            this.measureChild(var5, var1, var2);
//            var3 = var5.getMeasuredWidth();
//            var4 = var5.getMeasuredHeight();
//        } else {
//            AdSize var6 = null;
//
//            try {
//                var6 = this.getAdSize();
//            } catch (NullPointerException var8) {
//                zzbba.zzc("Unable to retrieve ad size.", var8);
//            }
//
//            if (var6 != null) {
//                Context var7 = this.getContext();
//                var3 = var6.getWidthInPixels(var7);
//                var4 = var6.getHeightInPixels(var7);
//            }
//        }
//
//        var3 = Math.max(var3, this.getSuggestedMinimumWidth());
//        var4 = Math.max(var4, this.getSuggestedMinimumHeight());
//        this.setMeasuredDimension(View.resolveSize(var3, var1), View.resolveSize(var4, var2));
//    }
}
