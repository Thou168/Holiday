package com.example.holiday.Buy_sell_rent.Sell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.holiday.Buy_sell_rent.Sell.Category.Computer_Accessories;
import com.example.holiday.Buy_sell_rent.Sell.Category.Electronic;
import com.example.holiday.Buy_sell_rent.Sell.Category.Phone_and_Tablet;
import com.example.holiday.Buy_sell_rent.Sell.Category.Vehicles;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.R;

import java.util.ArrayList;

public class Sell extends AppCompatActivity {

    ArrayList<Item_Post> item_type =new ArrayList<>();
    ArrayList<Item_Post>item_posts,item_phone,item_computer,item_motor,item_electronic;
    Window window;
    TextView tv_home,tv_computer,tv_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = (TextView)findViewById(R.id.toolbar_title);
        title.setText("Sell");

        tv_home = (TextView)findViewById(R.id.tv_home);
        tv_home.setVisibility(View.GONE);
        tv_phone = (TextView)findViewById(R.id.tv_iconphone);
        tv_phone.setVisibility(View.GONE);
        tv_computer = (TextView)findViewById(R.id.tv_computer);
        tv_computer.setVisibility(View.GONE);


        item_posts = new ArrayList<Item_Post>();
        item_posts = (ArrayList<Item_Post>)getIntent().getSerializableExtra("items");

        getPostbyType("Sell","Phone");
        item_phone = new ArrayList<>(item_type);
        findViewById(R.id.tv_iconphone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Phone_and_Tablet.class);
                intent.putExtra("title","Phone and Tablet");
                intent.putExtra("back","Sell");
                intent.putExtra("items",item_phone);
                startActivity(intent);
            }
        });
        item_type.clear();
        getPostbyType("Sell","Computer");
        item_computer = new ArrayList<>(item_type);
        findViewById(R.id.tv_computer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Computer_Accessories.class);
                intent.putExtra("title","Computer and Accessories");
                intent.putExtra("back","Sell");
                intent.putExtra("items",item_computer);
                startActivity(intent);
            }
        });
        item_type.clear();
        getPostbyType("Sell","Motor");
        item_motor = new ArrayList<>(item_type);
        findViewById(R.id.tv_motor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Vehicles.class);
                intent.putExtra("title","Vehicles");
                intent.putExtra("back","Sell");
                intent.putExtra("items",item_motor);
                startActivity(intent);
            }
        });
        item_type.clear();
        getPostbyType("Sell","Electronic");
        item_electronic = new ArrayList<>(item_type);
        findViewById(R.id.tv_electronic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Electronic.class);
                intent.putExtra("title","Electronic");
                intent.putExtra("back","Sell");
                intent.putExtra("items",item_electronic);
                startActivity(intent);
            }
        });
    }
    public ArrayList<Item_Post> getPostbyType(String post_type,String product_type){
        for(int i=0;i<item_posts.size();i++){
            if(item_posts.get(i).getPost_type().equals(post_type)){
                if(item_posts.get(i).getProduct_type().equals(product_type)){
                    item_type.add(item_posts.get(i));

                }
            }
        }
        return item_type;
    }
}
