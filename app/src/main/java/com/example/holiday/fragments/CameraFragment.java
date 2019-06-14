package com.example.holiday.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.Class_item.sheet;
import com.example.holiday.R;


public class CameraFragment extends Fragment {

    TextInputEditText textTitle;
    ImageView imgTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.post_ad,container,false);

        textTitle = (TextInputEditText) view.findViewById(R.id.title);
        imgTitle = (ImageView) view.findViewById(R.id.img_title);
        validateTitle();
        textTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textTitle.getWindowToken(), 0);
                    textTitle.clearFocus();
                    return true;
                }
                return false;
            }
        });
        return view;
    }
    private void validateTitle(){
        textTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    imgTitle.setImageResource(R.drawable.icon_null);
                } else if (s.length() < 3) {
                    imgTitle.setImageResource(R.drawable.ic_error_black_24dp);
                } else imgTitle.setImageResource(R.drawable.icon_ok);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
