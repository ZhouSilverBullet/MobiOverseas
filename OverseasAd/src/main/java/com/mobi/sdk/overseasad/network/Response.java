package com.mobi.sdk.overseasad.network;

import java.io.IOException;
import java.io.InputStream;

/**
 * Email: zhousaito@163.com
 * Created by zhousaito 2020/6/8 18:03
 * Version: 1.0
 * Description:
 */
public class Response {
    private int code;
    private String message;
    private String url;
    private Throwable e;
    private InputStream inputStream;

    public String body() {
        try {
            if (inputStream != null) {
                int len = -1;
                byte[] bs = new byte[1024 * 1024];
                StringBuilder sb = new StringBuilder();
                while ((len = inputStream.read(bs)) != -1) {
                    sb.append(new String(bs, 0, len));
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
