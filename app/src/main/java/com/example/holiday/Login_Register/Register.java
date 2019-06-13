package com.example.holiday.Login_Register;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.R;
import com.example.holiday.startup.MainActivity;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {
    ImageButton btnFacebookLogin;
    private Button btnSubmit;
    private EditText editPhone,editEmail,editPassword;
    private static final String TAG = "Response";
    TextView textView;
    private Context context;
    ProgressDialog mProgress;
    SharedPreferences prefer;
    String name;
    String email;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPassword = (EditText)findViewById(R.id.editConfirm);
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        prefer = getSharedPreferences("Register",MODE_PRIVATE);

        btnSubmit = (Button)findViewById(R.id.btnSub);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.show();
                try {
                    postRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                    mProgress.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                    mProgress.dismiss();
                }
            }
        });
    } // create

    public void postRequest() throws IOException, JSONException {
        name = editPhone.getText().toString();
        email= editEmail.getText().toString();
        pass = editPassword.getText().toString();


        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://192.168.1.239:8000/users/";   // register

        OkHttpClient client = new OkHttpClient();
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("username", name);
            postdata.put("email",email);
            postdata.put("password", pass);

        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                mProgress.dismiss();
                Toast.makeText(getApplicationContext(),"failure Response:"+mMessage,Toast.LENGTH_SHORT).show();
                //call.cancel();
            }


            @Override
            public void onResponse(Call call, final Response response) throws IOException {


                final String mMessage = response.body().string();
                Log.e(TAG, mMessage);
                converting(mMessage);

            }
        });
    }

    private void converting(String mMessage) {
        Gson gson = new Gson();
        Convert_Json_Java convertJsonJava = new Convert_Json_Java();
        try{
            convertJsonJava = gson.fromJson(mMessage,Convert_Json_Java.class);
            Log.d(TAG, convertJsonJava.getUsername() + "\t" + convertJsonJava.getEmail() + "\t" + convertJsonJava.getToken() + "\t" + convertJsonJava.getStatus());
            final String token = convertJsonJava.getToken();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (token!=null){
                        SharedPreferences.Editor editor =prefer.edit();
                        editor.putString("token",token);
                        editor.commit();

                        mProgress.dismiss();
                        Toast.makeText(getApplicationContext(),"Register Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }else {
                        mProgress.dismiss();
                        Toast.makeText(getApplicationContext(),"register failure",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (JsonParseException e){
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgress.dismiss();
                    Toast.makeText(getApplicationContext(),"register failure",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


}
