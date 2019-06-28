package com.example.holiday.api;

import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.holiday.YourPost_Like.List_Post;
import com.example.holiday.models.CategoryViewModel;
import com.example.holiday.models.YearViewModel;
//import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class CommonFunction {
    public static String getAuthenticationEncodeString(String username, String password){
        final String userAccount=String.format("%s:%s",username,password);
        return Base64.encodeToString(userAccount.getBytes(),Base64.NO_WRAP);
    }

    public static List<YearViewModel> getYearList(String username, String password){
        final List<YearViewModel> yearList = new ArrayList<YearViewModel>();
        String API_ENDPOINT= String.format("%s%s",ConsumeAPI.BASE_URL,"api/v1/years/");
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, API_ENDPOINT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray=response.getJSONArray("results");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        yearList.add(new YearViewModel(object.getInt("id"),object.getString("year")));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        return yearList;
    }

    public static List<CategoryViewModel> getCategoriesList(String username,String password){
        final List<CategoryViewModel> categories=new ArrayList<CategoryViewModel>();
        String API_ENDPOINT=String.format("%s%s",ConsumeAPI.BASE_URL,"api/v1/categories/");
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, API_ENDPOINT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray=response.getJSONArray("results");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        categories.add(new CategoryViewModel(object.getInt("id"),object.getString("cat_name"),object.getString("cat_name_kh"),object.getString("cat_description"),object.getString("cat_image_path"),object.getInt("record_status")));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        return categories;
    }
}
