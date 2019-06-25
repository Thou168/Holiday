package com.example.holiday.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.example.holiday.R;
import com.example.holiday.fragments.Choose_category.Choose_Category;

import static android.app.Activity.RESULT_OK;


public class CameraFragment extends Fragment {

    private TextView tvPostType,tvCategory,tvType_elec,tvBrand,tvModel,tvYear,tvCondition,tvColor,tvRent,tvDiscount_type;
    private EditText etTitle,etVinCode,etMachineCode,etDescription,etPice,etDiscount_amount,etName,etPhone1,etPhone2,etPhone3,etEmail;
    private ImageView icPostType,icCategory,icType_elec,icBrand,icModel,icYears,icCondition,icColor,icRent,icDiscount_type,
                        icTitile,icVincode,icMachineconde,icDescription,icPrice,icDiscount_amount,icName,icEmail,icPhone1,icPhone2,icPhone3;
    private static final int POST_TYPLE = 0;
    private static final int CATEGORY =1;
    private static final int TYPE_ELEC =2;
    private static final int BRAND = 3;
    private static final int MODEL =4;
    private static final int YEAR =5;
    private static final int CONDITION=6;
    private static final int COLOR=7;
    private static final int RENT=8;
    private static final int DISCOUNT_TYPE=9;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.post_ad,container,false);

//textview ///////
        tvPostType = (TextView)view.findViewById(R.id.tvPostType);
        tvCategory = (TextView)view.findViewById(R.id.tvCategory);
        tvType_elec= (TextView)view.findViewById(R.id.tvType_elec);
        tvBrand    = (TextView)view.findViewById(R.id.tvBrand);
        tvModel    = (TextView)view.findViewById(R.id.tvModel);
        tvYear     = (TextView)view.findViewById(R.id.tvYears);
        tvCondition= (TextView)view.findViewById(R.id.tvCondition);
        tvColor    = (TextView)view.findViewById(R.id.tvColor);
        tvRent     = (TextView)view.findViewById(R.id.tvRent);
        tvDiscount_type = (TextView)view.findViewById(R.id.tvDisType);
 // edit text ////

// icon  ////////
        icPostType   = (ImageView)view.findViewById(R.id.imgPostType);
        icCategory   = (ImageView)view.findViewById(R.id. imgCategory);
        icType_elec  = (ImageView)view.findViewById(R.id.imgType_elec );
        icBrand      = (ImageView)view.findViewById(R.id. imgBrand);
        icModel      = (ImageView)view.findViewById(R.id. imgModel);
        icYears      = (ImageView)view.findViewById(R.id. imgYear);
        icCondition  = (ImageView)view.findViewById(R.id. imgCondition);
        icColor      = (ImageView)view.findViewById(R.id. imgColor);
        icRent       = (ImageView)view.findViewById(R.id. imgRent);
        icTitile     = (ImageView)view.findViewById(R.id. imgTitle);
        icVincode    = (ImageView)view.findViewById(R.id. imgVinCode);
       icMachineconde= (ImageView)view.findViewById(R.id. imgMachineCode);
        icDescription= (ImageView)view.findViewById(R.id. imgDescription);
        icPrice      = (ImageView)view.findViewById(R.id. imgPrice);
        icName       = (ImageView)view.findViewById(R.id. imgName);
        icEmail      = (ImageView)view.findViewById(R.id. imgEmail);
        icPhone1     = (ImageView)view.findViewById(R.id. imgPhone1);
        icPhone2     = (ImageView)view.findViewById(R.id. imgPhone2 );
        icPhone3     = (ImageView)view.findViewById(R.id. imgPhone3);
        icDiscount_amount = (ImageView)view.findViewById(R.id. imgDisAmount);
        icDiscount_type = (ImageView)view.findViewById(R.id.imgDisType );



        Choose();
        return view;
    }// createview

    private void Choose() {
        tvPostType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","PostType");
                startActivityForResult(intent,POST_TYPLE);
            }
        });

        tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","Category");
                startActivityForResult(intent,CATEGORY);
            }
        });

        tvType_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","Type_elec");
                startActivityForResult(intent,TYPE_ELEC);
            }
        });

        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= tvCategory.getText().toString();
                String type_elec = tvType_elec.getText().toString();
                String st;
                if (category.equals("Vehicles")){
                    st=category;
                    Intent intent = new Intent(v.getContext(), Choose_Category.class);
                    intent.putExtra("Choose_category",st);
                    startActivityForResult(intent,BRAND);
                }else {
                    st= type_elec;
                    Intent intent = new Intent(v.getContext(), Choose_Category.class);
                    intent.putExtra("type_elec",st);
                    startActivityForResult(intent,BRAND);
                }


            }
        });

        tvModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","Years");
                startActivityForResult(intent,YEAR);
            }
        });

        tvCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","Condition");
                startActivityForResult(intent,CONDITION);
            }
        });

        tvRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","Rent");
                startActivityForResult(intent,RENT);
            }
        });

        tvDiscount_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","Discount_type");
                startActivityForResult(intent,DISCOUNT_TYPE);
            }
        });
    } //choose

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == POST_TYPLE && data!=null){
            String st = data.getStringExtra("field");
            if (st.equals("Rent")) {
                tvRent.setVisibility(View.VISIBLE);
            }else {
                tvRent.setVisibility(View.GONE);
                icRent.setVisibility(View.GONE);
            }
            tvPostType.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == CATEGORY && data!=null){
            String st = data.getStringExtra("field");
            if (st.equals("Vehicles")) {
                tvType_elec.setVisibility(View.GONE);
                icType_elec.setVisibility(View.GONE);
            }else {
                tvType_elec.setVisibility(View.VISIBLE);
                icType_elec.setVisibility(View.VISIBLE);
            }
            tvCategory.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == TYPE_ELEC && data!=null){
            String st = data.getStringExtra("field");

            tvType_elec.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == YEAR && data!=null){
            String st = data.getStringExtra("field");

            tvYear.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == CONDITION && data!=null){
            String st = data.getStringExtra("field");

            tvCondition.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == RENT && data!=null){
            String st = data.getStringExtra("field");

            tvRent.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == DISCOUNT_TYPE && data!=null){
            String st = data.getStringExtra("field");

            tvDiscount_type.setText(st);
        }

    }
}
