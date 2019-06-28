package com.example.holiday.fragments.Choose_category;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.LocaleData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.holiday.Edit_Account.Edit_account;
import com.example.holiday.R;
import com.example.holiday.api.CommonFunction;
import com.example.holiday.api.ConsumeAPI;
import com.example.holiday.fragments.CameraFragment;
import com.example.holiday.models.CategoryViewModel;
import com.example.holiday.models.YearViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Choose_Category extends AppCompatActivity {
    private TextView tv_toolbar;
    private ListView listView_Category;
    private TextView backCamera;
    private String[] PostType;
    private String[] Category;
    private String[] Type_elec;
    private String[] Motor_band;
    private String[] Air_conditioner_brand;
    private String[] TV_brand;
    private String[] Model;
    private String[] Years;
    private String[] Condition;
    private String[] Color;
    private String[] Rent;
    private String[] Discount_type;
    private ArrayAdapter<String> ad_PostType,ad_Type_elec,ad_Motor,ad_AirConditioner,ad_TV,ad_condition,ad_color,ad_Rent,ad_DiscountType;
    private ArrayAdapter<String> ad_Category_name,ad_Year_name;
    private ArrayAdapter<Integer> ad_Category_id,ad_Year_id;
    private String name,pass;
    private List<Integer> category_Id = new ArrayList<>();
    private List<Integer> year_Id= new ArrayList<>();
    private List<String>  category_name = new ArrayList<>();
    private List<String> year_name = new ArrayList<>();
    SharedPreferences prefer;
    String st;
    int position;
    String fields;
    String Encode;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose__category);
        mQueue=Volley.newRequestQueue(this);
        prefer = getSharedPreferences("Register",MODE_PRIVATE);
        name = prefer.getString("name","");
        pass = prefer.getString("pass","");

        Encode = getEncodedString(name,pass);
        final String auth = "Basic " + Encode;

        listView_Category = (ListView)findViewById(R.id.list_category);

        PostType  = getResources().getStringArray(R.array.post_type);
        Category  = getResources().getStringArray(R.array.category );
        Type_elec = getResources().getStringArray(R.array.type_elec );
        TV_brand  = getResources().getStringArray(R.array.tv_brand );
        Motor_band= getResources().getStringArray(R.array.motor_brand);
        Air_conditioner_brand = getResources().getStringArray(R.array.air_conditioner_brand );
        Years     = getResources().getStringArray(R.array.years );
        Condition = getResources().getStringArray(R.array.condition );
        Color     = getResources().getStringArray(R.array.color);
        Rent      = getResources().getStringArray(R.array.rent_type );
        Discount_type = getResources().getStringArray(R.array.discount_type );

        tv_toolbar = (TextView)findViewById(R.id.toolbar_chooseCategory);
        tv_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AdapterList();

        fields = getIntent().getExtras().getString("Choose_category");
        if (fields.equals("PostType")) {
            st = "PostType";
            listView_Category.setAdapter(ad_PostType);
        }
        else if (fields.equals("Category")) {
            st= "Category";
            String url =String.format("%s%s", ConsumeAPI.BASE_URL,"api/v1/categories/");
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                Log.d("Response",jsonArray.toString());
                                for (int i=0; i < jsonArray.length()  ; i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    Log.d("Response",object.toString());
                                    int id = object.getInt("id");
                                    String name = object.getString("cat_name");
                                    category_Id.add(id);
                                    category_name.add(name);
                                    ad_Category_id = new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_list_item_1,category_Id);
                                    ad_Category_name = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,category_name);

                                    listView_Category.setAdapter(ad_Category_name);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("Error Exception",e.getMessage());
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("VolleyError ",error.getMessage());
                }
            });
//            {
//                public Map<String,String> getHeaders() throws AuthFailureError{
//                    HashMap<String,String > headers = new HashMap<>();
//                    headers.put("Accept","application/json");
//                    headers.put("Content-Type","application/json");
//                    headers.put("Authorization",auth);
//                    return headers;
//                }
//            };
                mQueue.add(objectRequest);
        }
        else if (fields.equals("Type_elec")) {
            st = "Type_elec";
            listView_Category.setAdapter(ad_Type_elec);
        }
        else if (fields.equals("Vehicles")){
            st="Vehicles";
            listView_Category.setAdapter(ad_Motor);
        }else if (fields.equals("TV")){
            st="TV";
            listView_Category.setAdapter(ad_TV);
        }else if (fields.equals("Air-Conditioner")){
            st="Air-Conditioner";
            listView_Category.setAdapter(ad_AirConditioner);
        }
        else if (fields.equals("Years")) {
            st= "Years";
            List<YearViewModel> yearViewModels = new ArrayList<>();
            yearViewModels = CommonFunction.getYearList(name,pass) ;
            for (int i=0;i<yearViewModels.size();i++){
                int id = yearViewModels.get(i).getId();
                String name = yearViewModels.get(i).getYear();
                year_Id.add(id);
                year_name.add(name);
                ad_Year_id = new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_list_item_1,year_Id);
                ad_Year_name = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,year_name);
                listView_Category.setAdapter(ad_Year_name);

            }
        }
        else if (fields.equals("Condition")){
            st="Condition";
            listView_Category.setAdapter(ad_condition);
        }
        else if (fields.equals("Color")){
            st="Color";
            listView_Category.setAdapter(ad_color);
        }
        else if (fields.equals("Rent")){
            st="Rent";
            listView_Category.setAdapter(ad_Rent);
        }else if (fields.equals("Discount_type")){
            st="Discount_type";
            listView_Category.setAdapter(ad_DiscountType);
        }

        ListSelctItems(st);

    } //creatview



    private String getEncodedString(String username, String password) {
        final String userpass = username+":"+password;
        return Base64.encodeToString(userpass.getBytes(),
                Base64.NO_WRAP);
    }
    private void AdapterList() {
        ad_PostType = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, PostType);
    //    ad_Category = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Category );
        ad_Type_elec= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, Type_elec);
        ad_TV       = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,TV_brand );
        ad_Motor    = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Motor_band );
        ad_AirConditioner = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, Air_conditioner_brand );
   //     ad_Year     = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Years );
        ad_condition= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Condition );
        ad_color    = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Color);
        ad_Rent     = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Rent );
        ad_DiscountType= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Discount_type );

    }

    private void ListSelctItems(final String st) {
        listView_Category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ad = null;
                int ID=0;
                int yearID=0;
                if(st.equals("PostType")){ ad= ad_PostType.getItem(position);  }
                if (st.equals("Category"))
                {
                    ID = ad_Category_id.getItem(position);
                    ad= ad_Category_name.getItem(position);
                }
                if (st.equals("Type_elec")){ ad= ad_Type_elec.getItem(position); }

                if (st.equals("Vehicles")){ ad= ad_Motor.getItem(position);
                }else if (st.equals("TV")){       ad=ad_TV.getItem(position);

                }else if (st.equals("Air-Conditioner")){ ad=ad_AirConditioner.getItem(position);

                }

                else if (st.equals("Years")){
                    ID = ad_Year_id.getItem(position);
                    ad= ad_Year_name.getItem(position);

                }else if (st.equals("Condition")){ ad= ad_condition.getItem(position);

                }else if (st.equals("Color")){     ad= ad_color.getItem(position);

                }else if (st.equals("Rent")){      ad= ad_Rent.getItem(position);

                }else if (st.equals("Discount_type")){ ad= ad_DiscountType.getItem(position);

                }

                Intent intent = new Intent(getApplicationContext(), CameraFragment.class);
                intent.putExtra("id_year_category",ID);
                intent.putExtra("field",ad );
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }
}
