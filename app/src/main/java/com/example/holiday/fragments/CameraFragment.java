package com.example.holiday.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.R;
import com.example.holiday.api.ConsumeAPI;
import com.example.holiday.fragments.Choose_category.Choose_Category;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class CameraFragment extends Fragment {
    private static final String TAG = "Response";
    private TextView tvPostType,tvCategory,tvType_elec,tvBrand,tvModel,tvYear,tvCondition,tvColor,tvRent,tvDiscount_type;
    private EditText etTitle,etVinCode,etMachineCode,etDescription,etPrice,etDiscount_amount,etName,etPhone1,etPhone2,etPhone3,etEmail;
    private ImageView icPostType,icCategory,icType_elec,icBrand,icModel,icYears,icCondition,icColor,icRent,icDiscount_type,
                        icTitile,icVincode,icMachineconde,icDescription,icPrice,icDiscount_amount,icName,icEmail,icPhone1,icPhone2,icPhone3;
    private Button  submit_post;
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

    private int id,pk;
    private String name,pass,Encode,user_id;
    private SharedPreferences prefer;
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
        etTitle           = (EditText)view.findViewById(R.id.etTitle );
        etVinCode         = (EditText)view.findViewById(R.id.etVinCode );
        etMachineCode     = (EditText)view.findViewById(R.id.etMachineCode );
        etDescription     = (EditText)view.findViewById(R.id.etDescription );
        etPrice            = (EditText)view.findViewById(R.id.etPrice );
        etDiscount_amount = (EditText)view.findViewById(R.id.etDisAmount );
        etName            = (EditText)view.findViewById(R.id.etName );
        etPhone1          = (EditText)view.findViewById(R.id.etphone1 );
        etPhone2          = (EditText)view.findViewById(R.id.etphone2 );
        etPhone3          = (EditText)view.findViewById(R.id.etphone3 );
        etEmail           = (EditText)view.findViewById(R.id.etEmail );
        //// icon  ////////
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
        icDiscount_type   = (ImageView)view.findViewById(R.id.imgDisType );

        // get data from sharepreference
        prefer = this.getActivity().getSharedPreferences("Register",MODE_PRIVATE);
        if (prefer.contains("token")) {
            pk = prefer.getInt("Pk",0);
            user_id = String.valueOf(pk);
            Log.d(TAG, user_id);
        }else if (prefer.contains("id")) {
            id = prefer.getInt("id", 0);
            user_id = String.valueOf(id);
            Log.d(TAG, user_id);
        }
        name = prefer.getString("name","");
        pass = prefer.getString("pass","");
        Encode = getEncodedString(name,pass);
        submit_post  = (Button)view.findViewById(R.id.btnSubmitPost);
        submit_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostData(Encode);
            }
        });

        Choose();
        return view;
    }// createview

    private String getEncodedString(String username, String password) {
        final String userpass = username+":"+password;
        return Base64.encodeToString(userpass.getBytes(),
                Base64.NO_WRAP);
    }
    private void PostData(String encode) {
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url="";
        OkHttpClient client = new OkHttpClient();
        JSONObject post = new JSONObject();
        JSONObject sale = new JSONObject();
        try {

//            post.put("post_type",tvPostType.getText().toString().toLowerCase());
//            post.put("title",etTitle.getText().toString());
//            post.put("category",tvCategory.getText().toString());
//            post.put("type",tvType_elec.getText().toString());
//            post.put("discount","0.00");
//            post.put("created_by", user_id);
//            post.put("condition","new");
//            post.put("discount_type","amount");
//            post.put("contact_phone","012345620");
//            post.put("cost",0);


            String postType=tvPostType.getText().toString().toLowerCase();

            post.put("title",etTitle.getText().toString().toLowerCase() );
            post.put("category", tvCategory.getText().toString().toLowerCase());
            post.put("status", "");
            post.put("condition",tvCondition.getText().toString().toLowerCase() );
            post.put("discount_type", tvDiscount_type.getText().toString().toLowerCase() );
            post.put("discount", etDiscount_amount.getText().toString());
            post.put("user",null );
            post.put("front_image_path", null);
            post.put("right_image_path", null);
            post.put("left_image_path", null);
            post.put("back_image_path", null);
            post.put("created", "");
            post.put("created_by", user_id);
            post.put("modified", null);
            post.put("modified_by", null);
            post.put("approved_date", null);
            post.put("approved_by", null);
            post.put("rejected_date", null);
            post.put("rejected_by",null);
            post.put("rejected_comments", "");
            post.put("year", tvYear.getText().toString().toLowerCase());
            post.put("modeling", tvModel.getText().toString().toLowerCase());
            post.put("description", etDescription.getText().toString().toLowerCase());
            post.put("cost", etPrice.getText().toString().toLowerCase());
            post.put("post_type",tvPostType.getText().toString().toLowerCase() );
            post.put("vin_code", etVinCode.getText().toString().toLowerCase());
            post.put("machine_code", etMachineCode.getText().toString().toLowerCase());
            post.put("type", tvType_elec.getText().toString().toLowerCase());
            post.put("contact_phone", etPhone1.getText().toString().toLowerCase());
            post.put("contact_email", etEmail.getText().toString().toLowerCase() );
            post.put("contact_address", "");
            post.put("color", tvColor.getText().toString().toLowerCase());

            switch (postType){
                case "sell":
                    url=ConsumeAPI.BASE_URL+"postsale/";
                    Log.d("URL","URL"+url);
                    sale.put("sale_status", 2);
                    sale.put("record_status",2);
                    sale.put("sold_date", null);
                    sale.put("price", etPrice.getText().toString().toLowerCase());
                    sale.put("total_price", etPrice.getText().toString().toLowerCase());
                    post.put("sale_post",new JSONArray("["+sale+"]"));
                    break;
                case "rent":
                    url = String.format("%s%s", ConsumeAPI.BASE_URL, "postrent/");
                    JSONObject rent=new JSONObject();
                    rent.put("rent_status",1);
                    rent.put("record_status",1);
                    rent.put("rent_type",tvRent.getText().toString().toLowerCase());
                    rent.put("price",etPrice.getText().toString().toLowerCase());
                    rent.put("total_price",etPrice.getText().toString().toLowerCase());
                    rent.put("rent_date",null);
                    rent.put("return_date",null);
                    rent.put("rent_count_number",0);
                    post.put("rent_post",new JSONArray("["+rent+"]"));
                    break;
                case "buy":
                    url = String.format("%s%s", ConsumeAPI.BASE_URL, "api/v1/postbuys/");
                    JSONObject buy=new JSONObject();
                    buy.put("buy_status",1);
                    buy.put("record_status",1);
                    post.put("buy_post",new JSONArray("["+buy+"]"));
                    break;
            }
/*
            if(postType == "sell") {
                //url = String.format("%s%s", ConsumeAPI.BASE_URL, "");
                url=ConsumeAPI.BASE_URL+"postsale/";
                Log.d("URL","URL"+url);
                sale.put("sale_status", 2);
                sale.put("record_status",2);
                sale.put("sold_date", null);
                sale.put("price", etPrice.getText().toString().toLowerCase());
                sale.put("total_price", etPrice.getText().toString().toLowerCase());
                post.put("sale_post",new JSONArray("["+sale+"]"));
            }
            else if(postType=="rent") {
                url = String.format("%s%s", ConsumeAPI.BASE_URL, "postrent/");
                JSONObject rent=new JSONObject();
                rent.put("rent_status",1);
                rent.put("record_status",1);
                rent.put("rent_type",tvRent.getText().toString().toLowerCase());
                rent.put("price",etPrice.getText().toString().toLowerCase());
                rent.put("total_price",etPrice.getText().toString().toLowerCase());
                rent.put("rent_date",null);
                rent.put("return_date",null);
                rent.put("rent_count_number",0);
                post.put("rent_post",new JSONArray("["+rent+"]"));
            }
            else if(postType=="buy") {
                url = String.format("%s%s", ConsumeAPI.BASE_URL, "api/v1/postbuys/");

            }
*/
            RequestBody body = RequestBody.create(MEDIA_TYPE, post.toString());
            String auth = "Basic " + encode;
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization",auth)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    String mMessage = e.getMessage().toString();
                    Log.d("Failure:",mMessage );

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String message = response.body().string();
                    Log.d("Response",message);
                }
            });
         //   Log.d("Response",post.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    } //postdata


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

        tvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choose_Category.class);
                intent.putExtra("Choose_category","Color");
                startActivityForResult(intent,COLOR);
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
        }else if (resultCode == RESULT_OK && requestCode == COLOR  && data!=null){
            String st = data.getStringExtra("field");

            tvColor.setText(st);

        } else if (resultCode == RESULT_OK && requestCode == RENT && data!=null){
            String st = data.getStringExtra("field");

            tvRent.setText(st);
        }else if (resultCode == RESULT_OK && requestCode == DISCOUNT_TYPE && data!=null){
            String st = data.getStringExtra("field");

            tvDiscount_type.setText(st);
        }

    }
}
