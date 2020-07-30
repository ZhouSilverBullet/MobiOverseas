package com.mobi.sdk.overseasad;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.mobi.sdk.overseasad.network.Dispatcher;
import com.mobi.sdk.overseasad.utils.GaidInit;
import com.mobi.sdk.overseasad.utils.OAIdSdk;
import com.mobi.sdk.overseasad.utils.WeakHandler;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 15:45
 * @Dec 略
 */
public class OverseasAdSession implements OAIdSdk.ResultCallback, WeakHandler.IHandler {

    private Dispatcher mDispatcher;
    private WeakHandler mHandler;

    private OverseasAdSession() {
    }

    public static OverseasAdSession get() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void onResult(boolean isSupport, String oaid, String vaid, String aaid) {
        setOaid(oaid);
    }

    @Override
    public void handleMsg(Message msg) {

    }

    private static class SingletonHolder {
        private static final OverseasAdSession INSTANCE = new OverseasAdSession();
    }

    private Context mContext;
    private String mAppId;

    private volatile String gaid;
    private volatile String oaid;

    /**
     *
     * @param context
     */
    public void init(Context context, String appId) {
        mContext = context;
        mAppId = appId;
        mHandler = new WeakHandler(Looper.getMainLooper(), this);

        mDispatcher = new Dispatcher();
        GaidInit.inti(context);
        OAIdSdk.init(context, this);
    }

    public void setGaid(String gaid) {
        this.gaid = gaid;
    }

    public String getGaid() {
        if (TextUtils.isEmpty(gaid)) {
            gaid = "";
        }
        return gaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getOaid() {
        if (TextUtils.isEmpty(gaid)) {
            gaid = "";
        }
        return oaid;
    }

    public Context getContext() {
        return mContext;
    }

    public String getAppId() {
        return mAppId;
    }

    public Dispatcher getDispatcher() {
        return mDispatcher;
    }

    public void runUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }
}
