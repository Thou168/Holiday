package com.example.holiday.Buy_sell_rent.Rent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.holiday.Buy_sell_rent.Buy.Category.Phone_and_Tablet;
import com.example.holiday.Buy_sell_rent.Rent.Category.Home;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.R;

import java.util.ArrayList;

public class Rent extends AppCompatActivity {

    Window window;
    ArrayList<Item_Post> item_type = new ArrayList<>();
    ArrayList<Item_Post>item_posts,item_home;
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
        title.setText("Rent");

        item_posts = new ArrayList<Item_Post>();
        item_posts = (ArrayList<Item_Post>)getIntent().getSerializableExtra("items");

        getPostbyType("Rent","Home");
        item_home = new ArrayList<>(item_type);
        findViewById(R.id.tv_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Home.class);
                intent.putExtra("title","Home");
                intent.putExtra("back","Rent");
                intent.putExtra("items",item_home);
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
