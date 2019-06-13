package com.example.holiday.Setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.R;
import com.example.holiday.startup.MainActivity;

public class Setting extends AppCompatActivity {
    private TextView Changepassword, Language,Language2;
    private Button Logout;
    private Toolbar toolbar_setting;
    private SharedPreferences prefer;
    private  String[] listItems={"English","Khmer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        prefer = getSharedPreferences("Register",MODE_PRIVATE);

        Changepassword = (TextView)findViewById(R.id.tvChangePass);
        Language = (TextView)findViewById(R.id.tvLanguage);
        Language = (TextView)findViewById(R.id.tvLanguage2);
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
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(v.getContext());
                mBuilder.setTitle("Choose Language");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"click: "+ listItems,Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }
}
