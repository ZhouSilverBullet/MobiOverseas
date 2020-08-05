package com.mobi.sdk.overseasad.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobi.sdk.overseasad.MobiAdLoader;
import com.mobi.sdk.overseasad.MobiAdRequest;
import com.mobi.sdk.overseasad.MobiError;
import com.mobi.sdk.overseasad.MobiLoaderImpl;
import com.mobi.sdk.overseasad.OverseasAdSession;
import com.mobi.sdk.overseasad.listener.MobiBannerAd;
import com.mobi.sdk.overseasad.listener.MobiCallback;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/8/1 13:55
 * @Dec 这个是真正的给外界使用的
 */
public class MobiBannerView extends FrameLayout {

    private BannerAdListener mBannerAdListener;

    public interface BannerAdListener {
        public void onBannerLoaded(@NonNull View banner);

        public void onBannerFailed(View banner, MobiError error);

        public void onBannerClicked(View banner);

        public void onBannerExpanded(View banner);

        public void onBannerCollapsed(View banner);
    }

    interface MobiAdSizeInt {
        int MATCH_VIEW_INT = -1;
        int HEIGHT_50_INT = 50;
        int HEIGHT_90_INT = 90;
        int HEIGHT_250_INT = 250;
        int HEIGHT_280_INT = 280;
    }

    public enum MobiAdSize implements MobiAdSizeInt {
        MATCH_VIEW(MATCH_VIEW_INT),
        HEIGHT_50(HEIGHT_50_INT),
        HEIGHT_90(HEIGHT_90_INT),
        HEIGHT_250(HEIGHT_250_INT),
        HEIGHT_280(HEIGHT_280_INT);

        final private int mSizeInt;

        MobiAdSize(final int sizeInt) {
            this.mSizeInt = sizeInt;
        }

        /**
         * This valueOf overload is used to get the associated the MoPubAdSize enum from an int (likely
         * from XML layout).
         *
         * @param adSizeInt The int value for which the MoPubAdSize is needed.
         * @return The MoPubAdSize associated with the level. Will return CUSTOM by default.
         */
        @NonNull
        public static MobiAdSize valueOf(final int adSizeInt) {
            switch (adSizeInt) {
                case HEIGHT_50_INT:
                    return HEIGHT_50;
                case HEIGHT_90_INT:
                    return HEIGHT_90;
                case HEIGHT_250_INT:
                    return HEIGHT_250;
                case HEIGHT_280_INT:
                    return HEIGHT_280;
                case MATCH_VIEW_INT:
                default:
                    return MATCH_VIEW;
            }
        }

        public int toInt() {
            return mSizeInt;
        }
    }

    public static final String TAG = "MobiBannerView";

    public MobiBannerView(@NonNull Context context) {
        this(context, null);
    }

    public MobiBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MobiBannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //默认自己给本地进行初始化一下
        OverseasAdSession.get().init(context.getApplicationContext(), "");
    }

    /**
     * 加载
     *
     * @param mobiAdRequest
     */
    public void load(MobiAdRequest mobiAdRequest) {
        MobiAdLoader adLoader = new MobiLoaderImpl(getContext(), true);
        adLoader.loadBannerAd(mobiAdRequest, new MobiCallback.BannerAdLoadCallback() {
            @Override
            public void onBannerLoaded(MobiBannerAd bannerAd) {
                if (mBannerAdListener != null) {
                    mBannerAdListener.onBannerLoaded(MobiBannerView.this);
                }
                bannerAd.setAdBannerListener(new MobiBannerAd.AdListener() {
                    @Override
                    public void onBannerClicked(View bannerView, int index) {
                        if (mBannerAdListener != null) {
                            mBannerAdListener.onBannerClicked(bannerView);
                        }
                    }

                    @Override
                    public void onBannerExpanded(View bannerView, int index) {

                        addView(bannerView);

                        if (mBannerAdListener != null) {
                            mBannerAdListener.onBannerExpanded(bannerView);
                        }
                    }
                });
                bannerAd.render();
            }

            @Override
            public void onBannerFailed(int code, String message) {
                if (mBannerAdListener != null) {
                    mBannerAdListener.onBannerFailed(MobiBannerView.this, new MobiError(code, message));
                }
            }
        });
    }

    public void setAdListener(BannerAdListener bannerAdListener) {
        mBannerAdListener = bannerAdListener;
    }
}
