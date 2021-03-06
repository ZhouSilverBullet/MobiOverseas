package com.mobi.sdk.overseasad.network;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Email: zhousaito@163.com
 * Created by zhousaito 2020/6/8 17:48
 * Version: 1.0
 * Description:
 */
public class HttpClient {
    public static final int TIME_OUT = 20_000;

    public Response execute(Request request) {
        //request.url
        //判断一下request的值
        Response response = new Response();
        String requestUrl = request.getUrl();
        try {
            URL url = new URL(requestUrl);
            response.setUrl(requestUrl);

            HttpURLConnection conn = getConn(url);
            //处理头部
            for (String key : request.getHeaders().keySet()) {
                conn.setRequestProperty(key, request.getHeaders().get(key));
            }

            conn.setRequestMethod(request.getRequestMethod());

            //处理post的请求体
            if (request.getMethod() == Request.POST) {
                final String fromBody = request.getFromBody();
                if (fromBody != null) {
                    handleRequestBody(conn, fromBody, request.isGzipCompress());
                }
            }

            conn.connect();

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                response.setCode(HttpURLConnection.HTTP_OK);

                String encoding = conn.getContentEncoding();
                if ("gzip".equals(encoding)) {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(conn.getInputStream());
                    response.setInputStream(gZIPInputStream);
                } else {
                    response.setInputStream(conn.getInputStream());
                }

                return response;
            }

            response.setCode(code);
            response.setMessage(requestUrl + " | http 非 200");

        } catch (Exception e) {
            e.printStackTrace();

            response.setCode(0);
            response.setMessage(HttpUtil.getHttpExceptionMessage(e));
        }

        return response;
    }

    private void handleRequestBody(HttpURLConnection conn, String fromBody, boolean isGzipCompress) {
        conn.setDoOutput(true);
        try {
            if (isGzipCompress) {
                GZIPOutputStream outStream = new GZIPOutputStream(conn.getOutputStream());
                outStream.write(fromBody.getBytes());
                outStream.flush();
            } else {
                OutputStreamWriter out = new OutputStreamWriter(
                        conn.getOutputStream(), Charset.forName("UTF-8"));
                // 发送请求params参数
                out.write(fromBody);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpURLConnection getConn(URL url) throws IOException {
        final URLConnection connection = url.openConnection();
        connection.setReadTimeout(TIME_OUT);
        connection.setConnectTimeout(TIME_OUT);
        connection.setReadTimeout(TIME_OUT);
        // User-Agent  IE9的标识
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;");
        /*
         * 当我们要获取我们请求的http地址访问的数据时就是使用connection.getInputStream().read()方式时我们就需要setDoInput(true)，
         * 根据api文档我们可知doInput默认就是为true。我们可以不用手动设置了，如果不需要读取输入流的话那就setDoInput(false)。
         * 当我们要采用非get请求给一个http网络地址传参 就是使用connection.getOutputStream().write() 方法时我们就需要setDoOutput(true), 默认是false
         */
        // 设置是否从httpUrlConnection读入，默认情况下是true;
        connection.setDoInput(true);
        // 设置是否向httpUrlConnection输出，如果是post请求，参数要放在http正文内，因此需要设为true, 默认是false;
        //connection.setDoOutput(true);//Android  4.0 GET时候 用这句会变成POST  报错java.io.FileNotFoundException
        connection.setUseCaches(false);
        return (HttpURLConnection) connection;
    }
}
