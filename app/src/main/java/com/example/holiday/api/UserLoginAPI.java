package com.example.holiday.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UserLoginAPI {
    private static String URL_END_POINT="rest-auth/login/";

    public static void postRequest(String username,String password) throws IOException, JSONException{
        String API_URL=String.format("%s%s",ConsumeAPI.BASE_URL,URL_END_POINT);
        OkHttpClient client=new OkHttpClient();
        JSONObject postdata=new JSONObject();

        try{
            postdata.put("username",username);
            postdata.put("password",password);
        }catch (JSONException e){
            e.printStackTrace();
        }
        RequestBody body=RequestBody.create(ConsumeAPI.MEDIA_TYPE,postdata.toString());
        Request request=new Request.Builder()
                .url(API_URL)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

    }
}
