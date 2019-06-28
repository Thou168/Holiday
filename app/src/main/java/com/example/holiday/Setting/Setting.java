package com.example.holiday.Setting;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import com.example.holiday.R;
import com.example.holiday.startup.MainActivity;

import java.util.Locale;

public class Setting extends AppCompatActivity {
    private TextView Changepassword, Language,Language2;
    private Button Logout;
    private Toolbar toolbar_setting;
    private SharedPreferences prefer;
    private  String[] listItems={"English","Khmer"};
    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loanlocale();
        setContentView(R.layout.activity_setting);
        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorMessage));
        prefer = getSharedPreferences("Register",MODE_PRIVATE);

        Changepassword = (TextView)findViewById(R.id.tvChangePass);
        Language = (TextView)findViewById(R.id.tvLanguage);
        Language2 = (TextView)findViewById(R.id.tvLanguage2);
        Logout = (Button)findViewById(R.id.btnlogout);
        toolbar_setting = (Toolbar)findViewById(R.id.toolbar_setting);

        toolbar_setting.setTitle("");
        toolbar_setting.setNavigationIcon(R.drawable.icon_back);
        toolbar_setting.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),Change_Password.class));
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(v.getContext())
                        .setMessage("Are you sure you want to logout?")
                        .setIcon(R.drawable.logo)
                        .setPositiveButton(Html.fromHtml("<font color='#F30E0E'>Logout</font>"),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        SharedPreferences.Editor editor = prefer.edit();
                                        editor.clear();
                                        editor.commit();
                                        dialog.cancel();
                                        startActivity(new Intent(Setting.this, MainActivity.class));
                                    }
                                })
                        .setNegativeButton(Html.fromHtml("<font color='#1616FA'>Cancel</font>"), new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        Language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showChangeLanguage();
            }
        });
        Language2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguage();
            }
        });

    } // create

    private  void  showChangeLanguage(){
        final String[] list = {"English","Khmer"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Setting.this);
        mBuilder.setTitle("Choose Language....");
        mBuilder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    setLocale("en");
                    recreate();
                }else if (which == 1){
                    setLocale("km");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mdialog = mBuilder.create();
        mBuilder.show();
    }

    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration confi = new Configuration();
        confi.locale = locale;
        getBaseContext().getResources().updateConfiguration(confi,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loanlocale(){
        SharedPreferences prefer = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefer.getString("My_Lang","");
        setLocale(language);
    }
}
