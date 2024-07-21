package com.devjin.springstu.domain.service;

import lombok.RequiredArgsConstructor;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Handler;
@Service
@RequiredArgsConstructor
public class WebService {

    public JSONObject get(final String requestUrl){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet getrequest = new HttpGet(requestUrl);
        try {
            CloseableHttpResponse response = client.execute(getrequest);
            if(response.getCode() == 200){

                HttpEntity entity = response.getEntity();

                JSONObject job = new JSONObject(EntityUtils.toString(entity));

                return job;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
