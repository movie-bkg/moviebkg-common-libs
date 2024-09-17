package com.sid.moviebkg.common.resttemplate.interceptor;

import com.sid.moviebkg.common.logging.MBkgLogger;
import com.sid.moviebkg.common.logging.MBkgLoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private MBkgLogger logger = MBkgLoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        try {
            ClientHttpResponse response = execution.execute(request, body);
            traceResponse(response);
            return response;
        } catch (Exception e) {
            logger.warn("Error occurred during http call", e);
            throw e;
        }
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        logger.info("URI:{}, Method:{}, Headers:{}, Request body:{}",
                request.getURI(), request.getMethod(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        if (response != null) {
            logger.info("Response Status code:{}, Headers:{}", response.getStatusCode(), response.getHeaders());
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(getResponseBody(response)), StandardCharsets.UTF_8));
                String line = bufferedReader.readLine();
                while (line != null) {
                    inputStringBuilder.append(line);
                    inputStringBuilder.append('\n');
                    line = bufferedReader.readLine();
                }
                logger.info("Response body:{}", inputStringBuilder.toString());
            } catch (Exception e) {
                logger.warn("traceResponse Error occurred", e.getMessage());
                throw e;
            }
        }
    }

    private byte[] getResponseBody(ClientHttpResponse response) {
        byte[] resp;
        try {
            resp = FileCopyUtils.copyToByteArray(response.getBody());
        } catch (IOException e) {
            resp = new byte[0];
        }
        return resp;
    }
}
