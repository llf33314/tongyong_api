package com.gt.api.util.sign;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * Request body 重写类
 * Created by psr on 2017/8/4 0004.
 */
public class BodyRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body; // 用于保存读取body中数据

    public BodyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = HttpUtils.getBodyString(request).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException{
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            public boolean isFinished() { return false; }

            public boolean isReady() { return false; }

            public void setReadListener(ReadListener readListener) { }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public String getHeader(String name) {
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return super.getHeaderNames();
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        return super.getHeaders(name);
    }

}
