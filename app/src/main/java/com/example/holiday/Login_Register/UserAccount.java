package com.example.holiday.Login_Register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class UserAccount extends AppCompatActivity {
  //  private LoginButton LoginFacebook;
    private CallbackManager callbackManager;
    private TextView show;
    private TextView emailfb;
    private TextView gender;
    private TextView facebookName;
    private Context mContext;
    private ProfilePictureView profilePictureView;
    private Button btnLogin,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_user_account);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister=(Button)findViewById(R.id.btnRegister);
   //     LoginFacebook = (LoginButton) findViewById(R.id.login_button);

//        show = (TextView)findViewById(R.id.txtshow);
//        emailfb = (TextView)findViewById(R.id.emailfb);
//        facebookName = (TextView)findViewById(R.id.namefb);
//        gender = (TextView)findViewById(R.id.genderfb);
//        profilePictureView = (ProfilePictureView) findViewById(R.id.imagefb);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Login",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(),Login.class));

            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Register",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(),Register.class));

            }
        });

//        LoginFacebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                LoginManager.getInstance().logInWithReadPermissions((Activity) v.getContext(), Arrays.asList("public_profile"," email "));
//                callbackManager = CallbackManager.Factory.create();
//                Login();
//            }
//        });
    } // create

//    private void Login() {
//
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                try {
//                                    String id = object.getString("id");
//                                    String firstName = object.getString("first_name");
//                                    String lastName = object.getString("last_name");
//
////                                    emailfb.setText(object.getString("email"));
////                                    facebookName.setText(firstName +" "+ lastName);
////                                    profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
////                                    profilePictureView.setProfileId(id);
//
//                                    Bundle arg = new Bundle();
//                                    arg.putString("Name",firstName + " " + lastName);
//                                    arg.putInt("Pic", object.getInt("picture") );
//
////                                    Fragment fragment = new fram_account();
////                                    fragment.setArguments(arg);
////                                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                                    transaction.replace(R.id.com_facebook_fragment_container,fragment);
////                                    transaction.commit();
//
////                                    Intent intent = new Intent(UserAccountRegisterFacebookActivity.this,MainActivity.class);
////                                    startActivity(intent);
//
//                                } catch(JSONException e) {
//                                    Log.d("FB","Error parsing JSON");
//                                }
//                            }
//                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,first_name,last_name,email,picture");
//                request.setParameters(parameters);
//                request.executeAsync();
//
//            }
//
//            @Override
//            public void onCancel() {
//                show.setText("Cancel");
//            }
//            @Override
//            public void onError(FacebookException error) {
//                show.setText("Error: " + error.getMessage());
//            }
//        });
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }

}
