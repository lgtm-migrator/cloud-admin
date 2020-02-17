package com.hb0730.cloud.admin.common.util;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OkHttpClientUtilTest {

    @Test
    public void testPost() {
        String url = "http://localhost:1040/oauth/token";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("username", "test2")
                .add("password", "1234")
                .add("grant_type", "password")
                .add("client_id", "client")
                .add("client_secret", "secret")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
