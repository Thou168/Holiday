package com.example.holiday.Edit_Account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.holiday.Login_Register.Convert.Convert_Json_Java;
import com.example.holiday.R;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Edit_account extends AppCompatActivity {
    private static final String TAG = "Response";
    private LinearLayout layout_public_user,layout_121_dealer;
    private TextView tvType_121;
    private EditText etShopName,etShopAddr,etResponsible,etWingNumber_121,etWingName_121,etPhone_121;
    private TextView tvType,tvGender,tvPob,tvLocation,tvAddress,tvMarried;
    private EditText etUsername,etDob,etJob,etWingNumber,etWingName,etPhone;
    private TextInputLayout tilusername,tildob,tiljob,tilwingnumber,tilwingname,tilphone,tilShopName,tilshopAddr,tilresponsible;
    private ImageView imgType,imgGender,imgPob,imgLocation,imgAddress,imgMarried,imgtilUsername,imgtilDob,imgtilWingNumber,
                    imgtilWingName,imgtilPhone,imgtilShopName,imgtilShopAddr,imgtilResponsible;
    private Button btnsubmit;
    private Toolbar toolbar_account;
    private TextView tvBack;
    private static final int TYPE= 0;
    private static final int GENDER=1;
    private static final int POB=2;
    private static final int LOCATION=3;
    private static final int MARRIED=4;
    private SharedPreferences prefer;
    private int pk ,id;
    private String type,gender,pob,location,address,married,username,dob,job,wingnumber,wingname,phone,shop_name,shop_addr,responsible_name;
    private String name,pass,Encode,user_id;
    private RequestQueue mQueue;
    private String id_provinces,st;
    private int id_type,ID_type;
    private int g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account);

        layout_public_user = (LinearLayout)findViewById(R.id.layout_type_public_user);
        layout_121_dealer  = (LinearLayout)findViewById(R.id.layout_type_121_dealer);

// public user textview
        tvType     =(TextView) layout_public_user.findViewById(R.id.tvType);
        tvGender   = (TextView)layout_public_user.findViewById(R.id.tvGender);
        tvPob      =(TextView) layout_public_user.findViewById(R.id.tvPob);
        tvLocation = (TextView)layout_public_user.findViewById(R.id.tvLocation);
        tvMarried  = (TextView)layout_public_user.findViewById(R.id.tvMarried);
//public user edittext
        etUsername  = (EditText)layout_public_user.findViewById(R.id.etUsername);
        etDob       = (EditText)layout_public_user.findViewById(R.id.etDOB );
        etJob       = (EditText)layout_public_user.findViewById(R.id.etJob );
        etWingNumber= (EditText)layout_public_user.findViewById(R.id.etWingNumber );
        etWingName  = (EditText)layout_public_user.findViewById(R.id.etWingName );
        etPhone     = (EditText)layout_public_user.findViewById(R.id.etAccount_Phone );
        etShopName  = (EditText)layout_public_user.findViewById(R.id.etShop_Name);
        etShopAddr  = (EditText)layout_public_user.findViewById(R.id. etShop_Addr);
       etResponsible= (EditText)layout_public_user.findViewById(R.id.etResponsible );

// 121 dealer TextView
        tvType_121 = (TextView) layout_121_dealer.findViewById(R.id.tvType);

// 121 dealer EditText
        etShopName       =(EditText)layout_121_dealer.findViewById(R.id.etShop_Name);
        etShopAddr       =(EditText)layout_121_dealer.findViewById(R.id.etShop_Addr );
        etWingName_121   =(EditText)layout_121_dealer.findViewById(R.id.etWingName );
        etWingNumber_121 =(EditText)layout_121_dealer.findViewById(R.id.etWingNumber );
        etResponsible    =(EditText)layout_121_dealer.findViewById(R.id.etResponsible );
        etPhone_121      =(EditText)layout_121_dealer.findViewById(R.id.etAccount_Phone );

        btnsubmit = (Button)findViewById(R.id.btn_EditAccount);
      tvBack = (TextView)findViewById(R.id.tvBack_account);
      tvBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });

// textinputlayout
//      tilusername= (TextInputLayout)findViewById(R.id.tilUsername );
//      tildob     = (TextInputLayout)findViewById(R.id.tilDob );
//      tiljob     = (TextInputLayout)findViewById(R.id.tilJob );
//      tilwingnumber= (TextInputLayout)findViewById(R.id.tilWingNumber );
//      tilwingname= (TextInputLayout)findViewById(R.id.tilWingName );
//      tilphone   = (TextInputLayout)findViewById(R.id.tilAccount_phone );
//      tilShopName= (TextInputLayout)findViewById(R.id.tilShop_Name);
//      tilshopAddr= (TextInputLayout)findViewById(R.id.tilShop_Addr );
//      tilresponsible= (TextInputLayout)findViewById(R.id.tilResponsible );

      imgType            = (ImageView)findViewById(R.id.imgType );
      imgGender          = (ImageView)findViewById(R.id.imgGender );
      imgPob             = (ImageView)findViewById(R.id.imgPob );
      imgLocation        = (ImageView)findViewById(R.id.imgLocation );
      imgAddress         = (ImageView)findViewById(R.id.imgAccount_Address );
      imgMarried         = (ImageView)findViewById(R.id.imgMarried );
      imgtilUsername     = (ImageView)findViewById(R.id.imgUsername);
      imgtilDob          = (ImageView)findViewById(R.id.imgDob );
      imgtilWingNumber   = (ImageView)findViewById(R.id.imgWingNumber );
      imgtilWingName     = (ImageView)findViewById(R.id.imgWingName );
      imgtilPhone        = (ImageView)findViewById(R.id.imgAccount_Phone );
      imgtilShopName     = (ImageView)findViewById(R.id.imgShop_name);
      imgtilShopAddr     = (ImageView)findViewById(R.id.imgShop_Addr);
      imgtilResponsible  = (ImageView)findViewById(R.id.imgResponsible );




        prefer = getSharedPreferences("Register",MODE_PRIVATE);
        if (prefer.contains("token")) {
            pk = prefer.getInt("Pk",0);
            user_id = String.valueOf(pk);
            Log.d(TAG, user_id);
        }else if (prefer.contains("id")) {
            id = prefer.getInt("id", 0);
            user_id = String.valueOf(id);
            Log.d(TAG, user_id);
        }
        final String url = "http://192.168.1.239:7000/api/v1/users/"+user_id+"/";

        name = prefer.getString("name","");
        pass = prefer.getString("pass","");
        Encode = getEncodedString(name,pass);

        Groups(url,Encode);
        Choose();



        btnsubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            PutData(url,Encode);
          }
      });


    }  // create



    private void PutData(String url,String encode) {

        String t = tvType.getText().toString();

        username  = etUsername.getText().toString();
        gender    = tvGender.getText().toString().toLowerCase();
        dob       = etDob.getText().toString();
        pob       = tvPob.getText().toString();
        married   = tvMarried.getText().toString().toLowerCase();
        phone     = etPhone.getText().toString();
        location  = tvLocation.getText().toString();
        job       = etJob.getText().toString();
        shop_name        = etShopName.getText().toString();
        shop_addr        = etShopAddr.getText().toString();
        responsible_name = etResponsible.getText().toString();

        if (t.equals("Public User") || t.equals("Dealer")){
            wingnumber = etWingNumber.getText().toString();
            wingname   = etWingName.getText().toString();
        }else if (t.equals("121 Dealer")){
            wingnumber = etWingNumber_121.getText().toString();
            wingname   = etWingName_121.getText().toString();
        }

        MediaType media = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient();
        JSONObject data = new JSONObject();
        JSONObject pro  = new JSONObject();

        Log.d("type:", String.valueOf(ID_type));


        try{
            data.put("username",name);
            data.put("password",pass);
            data.put("first_name",username);

            pro.put("gender",gender);
            pro.put("data_of_birth",dob);
            pro.put("address",id_provinces); /////////////
            pro.put("shop_name",shop_name);
            pro.put("responsible_officer",responsible_name);
            pro.put("job",job);
            pro.put("province",id_provinces); /////////////
            pro.put("marital_status",married);
            pro.put("shop_address",shop_addr);
            pro.put("wing_account_number",wingnumber);
            pro.put("wing_account_name",wingname);

            pro.put("place_of_birth",id_provinces);
            data.put("profile",pro);

            data.put("groups", new JSONArray("[\"1\"]"));
            //data.put("groups",new JSONArray("["+id_type+"]"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        String auth = "Basic " + encode;
        RequestBody body = RequestBody.create(media, data.toString());
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",auth)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String message = e.getMessage().toString();
                Log.d("failure Response",message);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                Log.d("Response",message);
                finish();
            }
        });


    }

    private String getEncodedString(String username, String password) {
        final String userpass = username+":"+password;
        return Base64.encodeToString(userpass.getBytes(),
                Base64.NO_WRAP);
    }

    private void Groups(String url,String encode){
        MediaType MEDIA_TYPE     =  MediaType.parse("application/json");

        Log.d(TAG,url);

        OkHttpClient client = new OkHttpClient();

        String auth = "Basic "+ encode;
        Request request = new Request.Builder()
                .url(url)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",auth)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e(TAG,mMessage);
                Gson gson = new Gson();
                Convert_Json_Java convertJsonJava = new Convert_Json_Java();
                try{
                    convertJsonJava = gson.fromJson(mMessage,Convert_Json_Java.class);
                    int[] gg = convertJsonJava.getGroups();
                    g = gg[0];
                    Log.d(TAG,"Groups:" + g);
                    if (g==1){
                        tvType.setText("Public User");

                    }else if (g==2){
                        tvType.setText("121 Dealer");

                    }else if (g==3){
                        tvType.setText("Dealer");

                    }

                }catch (JsonParseException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void Choose() {
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Choose_type.class);
                intent.putExtra("Choose_Fields","Type");
                startActivityForResult(intent,TYPE);
            }
        });

        tvType_121.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Choose_type.class);
                intent.putExtra("Choose_Fields","Type");
                startActivityForResult(intent,TYPE);
            }
        });
        tvGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Choose_type.class);
                intent.putExtra("Choose_Fields","Gender");
                startActivityForResult(intent,GENDER);
            }
        });

        tvPob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Choose_type.class);
                intent.putExtra("Choose_Fields","POB");
                startActivityForResult(intent,POB);
            }
        });

        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Choose_type.class);
                intent.putExtra("Choose_Fields","Location");
                startActivityForResult(intent,LOCATION);
            }
        });

        tvMarried.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Choose_type.class);
                intent.putExtra("Choose_Fields","Married");
                startActivityForResult(intent,MARRIED);
            }
        });


    }

    private void UserInformation(String st) {
        if (st.equals("Public User")){
            layout_public_user.setVisibility(LinearLayout.VISIBLE);
            layout_121_dealer.setVisibility(LinearLayout.GONE);
        }else if (st.equals("121 Dealer")){
            layout_121_dealer.setVisibility(LinearLayout.VISIBLE);
            layout_public_user.setVisibility(LinearLayout.GONE);

        }else if (st.equals("Dealer")){
            layout_public_user.setVisibility(LinearLayout.VISIBLE);
            layout_121_dealer.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == TYPE && data!=null){
             st = data.getStringExtra("field");
            id_type = data.getIntExtra("id_type",0);
            ID_type = id_type;
            Log.d("Response type:", String.valueOf(id_type));
            tvType.setText(st);
            tvType_121.setText(st);
            UserInformation(st);
        }else if (resultCode == RESULT_OK && requestCode == GENDER && data!=null){
            String st = data.getStringExtra("field");
            tvGender.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == POB && data!=null) {
            String st = data.getStringExtra("field");
            id_provinces = data.getStringExtra("id_province");
                tvPob.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == LOCATION && data!=null) {
            String st = data.getStringExtra("field");
            id_provinces = data.getStringExtra("id_province");
            tvLocation.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == MARRIED && data!=null) {
            String st = data.getStringExtra("field");
            tvMarried.setText(st);
        }
    }
}
