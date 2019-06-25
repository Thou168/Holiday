package com.example.holiday.fragments.Choose_category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.Edit_Account.Edit_account;
import com.example.holiday.R;
import com.example.holiday.fragments.CameraFragment;

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
    private ArrayAdapter<String> ad_PostType,ad_Category,ad_Type_elec,ad_Motor,ad_AirConditioner,ad_TV,ad_Year,ad_condition,ad_Rent,ad_DiscountType;

    String st;
    int position;
    String fields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose__category);

        listView_Category = (ListView)findViewById(R.id.list_category);

        PostType = getResources().getStringArray(R.array.post_type);
        Category = getResources().getStringArray(R.array.category );
        Type_elec= getResources().getStringArray(R.array.type_elec );
        TV_brand = getResources().getStringArray(R.array.tv_brand );
        Motor_band= getResources().getStringArray(R.array.motor_brand);
        Air_conditioner_brand = getResources().getStringArray(R.array.air_conditioner_brand );
        Years  = getResources().getStringArray(R.array.years );
        Condition = getResources().getStringArray(R.array.condition );
        Rent  = getResources().getStringArray(R.array.rent_type );
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
            listView_Category.setAdapter(ad_Category);
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
            listView_Category.setAdapter(ad_Year);
        }
        else if (fields.equals("Condition")){
            st="Condition";
            listView_Category.setAdapter(ad_condition);
        }else if (fields.equals("Rent")){
            st="Rent";
            listView_Category.setAdapter(ad_Rent);
        }else if (fields.equals("Discount_type")){
            st="Discount_type";
            listView_Category.setAdapter(ad_DiscountType);
        }

        ListSelctItems(st);

    } //creatview



    private void AdapterList() {
        ad_PostType = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, PostType);
        ad_Category = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Category );
        ad_Type_elec= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, Type_elec);
        ad_TV       = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,TV_brand );
        ad_Motor    = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Motor_band );
        ad_AirConditioner = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, Air_conditioner_brand );
        ad_Year     = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Years );
        ad_condition= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Condition );
        ad_Rent     = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Rent );
        ad_DiscountType= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,Discount_type );

    }

    private void ListSelctItems(final String st) {
        listView_Category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ad = null;
                if(st.equals("PostType")){ ad= ad_PostType.getItem(position);  }
                if (st.equals("Category")){ ad= ad_Category.getItem(position); }
                if (st.equals("Type_elec")){ ad= ad_Type_elec.getItem(position); }

                if (st.equals("Vehicles")){ ad= ad_Motor.getItem(position);
                }else if (st.equals("TV")){       ad=ad_TV.getItem(position);

                }else if (st.equals("Air-Conditioner")){ ad=ad_AirConditioner.getItem(position);

                }

                else if (st.equals("Years")){  ad= ad_Year.getItem(position);

                }else if (st.equals("Condition")){ ad= ad_condition.getItem(position);

                }else if (st.equals("Rent")){ ad= ad_Rent.getItem(position);

                }else if (st.equals("Discount_type")){ ad= ad_DiscountType.getItem(position);

                }

                Intent intent = new Intent(getApplicationContext(), CameraFragment.class);
                intent.putExtra("field",ad );
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }
}
