package com.devjin.springstu.domain.service;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Handler;
@Service
@RequiredArgsConstructor
public class WebService {

    public JSONObject get(final String requestUrl){
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getrequest = new HttpGet(requestUrl);

        // addHeader --

        try {
            HttpResponse response = client.execute(getrequest);
            if(response.getStatusLine().getStatusCode() == 200){

                String result = EntityUtils.toString(response.getEntity());
                JSONObject job = new JSONObject(result);
                return job;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
