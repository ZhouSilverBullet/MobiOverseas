package com.mobi.sdk.overseasad.bean;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/30 15:49
 * @Dec 略
 */
public class TactBean {
    /**
     * pcache : 60
     * freq_time : 60
     * freq_count : 60
     */

    private int pcache;
    private int freqTime;
    private int freqCount;

    public int getPcache() {
        return pcache;
    }

    public void setPcache(int pcache) {
        this.pcache = pcache;
    }

    public int getFreqTime() {
        return freqTime;
    }

    public void setFreqTime(int freqTime) {
        this.freqTime = freqTime;
    }

    public int getFreqCount() {
        return freqCount;
    }

    public void setFreqCount(int freqCount) {
        this.freqCount = freqCount;
    }

    public TactBean(int pcache, int freqTime, int freqCount) {
        this.pcache = pcache;
        this.freqTime = freqTime;
        this.freqCount = freqCount;
    }

    public TactBean() {
    }
}
