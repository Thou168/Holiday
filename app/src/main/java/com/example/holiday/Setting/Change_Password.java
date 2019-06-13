package com.example.holiday.Setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.holiday.R;

public class Change_Password extends AppCompatActivity {
    private EditText Oldpass,Newpass,Comfirmpass;
    private Button Submit;
    private Toolbar toolbar_changepassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__password);

        Oldpass = (EditText) findViewById(R.id.etOldpass);
        Newpass = (EditText) findViewById(R.id.etNewpass);
        Comfirmpass = (EditText)findViewById(R.id.etComfirmpass);
        toolbar_changepassword = (Toolbar)findViewById(R.id.toolbar_changepassword);

        toolbar_changepassword.setTitle("");
        toolbar_changepassword.setNavigationIcon(R.drawable.icon_back);
        toolbar_changepassword.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Submit = (Button)findViewById(R.id.btnChangPassword);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Click",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
