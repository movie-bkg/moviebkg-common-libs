package com.sid.moviebkg.common.request;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

public class HttpServletRequestWrapper extends ContentCachingRequestWrapper {

    private SimpleServletInputStream inputStream;

    public HttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public SimpleServletInputStream getInputStream() {
        return inputStream;
    }

    public String readInputAndDuplicate() throws IOException {
        if (inputStream == null) {
            byte[] body = super.getInputStream().readAllBytes();
            this.inputStream = new SimpleServletInputStream(body);
        }
        return new String(super.getContentAsByteArray());
    }
}
