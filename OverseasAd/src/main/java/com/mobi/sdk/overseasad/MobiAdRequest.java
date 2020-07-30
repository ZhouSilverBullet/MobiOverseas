package com.mobi.sdk.overseasad;

/**
 * @author zhousaito
 * @version 1.0
 * @date 2020/7/29 16:11
 * @Dec 略
 */
public class MobiAdRequest {
    private String posid;

    private MobiAdRequest(Builder builder) {
        posid = builder.posid;
    }

    public String getPosid() {
        return posid;
    }

    public static final class Builder {
        private String posid;

        public Builder() {
        }

        public Builder setPosid(String posid) {
            this.posid = posid;
            return this;
        }

        public MobiAdRequest build() {
            return new MobiAdRequest(this);
        }
    }
}
