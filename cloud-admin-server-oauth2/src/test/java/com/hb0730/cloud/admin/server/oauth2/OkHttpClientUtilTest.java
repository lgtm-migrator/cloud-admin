package com.hb0730.cloud.admin.server.oauth2;

import com.google.common.collect.Maps;
import com.hb0730.cloud.admin.common.util.OkHttpClientUtil;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class OkHttpClientUtilTest {
    @Test
    public void testPost() {
        String url = "http://localhost:1040/oauth/token";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("username", "test2")
                .add("password", "123456")
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

    @Test
    public void testUtils() throws IOException {
        HashMap<String, String> paramMap = Maps.newHashMap();
        paramMap.put("username", "test1");
        paramMap.put("password", "123456");
        paramMap.put("grant_type", "password");
        paramMap.put("client_id", "client");
        paramMap.put("client_secret", "secret");
        Response response = OkHttpClientUtil.getInstance().postData("http://localhost:1040/oauth/token", paramMap);
        System.out.println(Objects.requireNonNull(response.body()).string());
    }
}
