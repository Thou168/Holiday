package com.example.holiday.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.holiday.Class_item.sheet;
import com.example.holiday.R;

public class CameraFragment extends Fragment {

    public CameraFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.post_ad,container,false);

        EditText textView = (EditText) view.findViewById(R.id.choose);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheet sheet = new sheet();
                sheet.show(getFragmentManager(),"example");

            }
        });

        return view;
    }

}
