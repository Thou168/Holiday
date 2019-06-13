package com.example.holiday.Product_discount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.holiday.Product_discount.Adapter.two_conlumn;
import com.example.holiday.Product_discount.Adapter.one_conlumn;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.R;

import java.util.ArrayList;
import java.util.List;

public class Discount_more_data extends AppCompatActivity {

    List<Item_Post>items;
    RecyclerView recy_horizontal1,recy_swap;
    RelativeLayout one,two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_more_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.txt_pro_discont);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        one = (RelativeLayout) findViewById(R.id.one);
        two = (RelativeLayout) findViewById(R.id.two);

        items = new ArrayList<Item_Post>();
        items = (ArrayList<Item_Post>)getIntent().getSerializableExtra("items");

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        two_conlumn adapter1 = new two_conlumn(getBaseContext(), (ArrayList<Item_Post>) items);
        recy_horizontal1 = (RecyclerView) findViewById(R.id.list_dis);
        recy_horizontal1.setHasFixedSize(true);
        recy_horizontal1.setLayoutManager(layoutManager1);
        recy_horizontal1.setAdapter(adapter1);
        GridLayoutManager manager = new GridLayoutManager(getBaseContext(), 2, GridLayoutManager.VERTICAL, false);
        recy_horizontal1.setLayoutManager(manager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        one_conlumn adapter = new one_conlumn(getBaseContext(), (ArrayList<Item_Post>) items);
        recy_swap = (RecyclerView) findViewById(R.id.recy_swap);
        recy_swap.setHasFixedSize(true);
        recy_swap.setLayoutManager(layoutManager1);
        recy_swap.setAdapter(adapter);
        GridLayoutManager manager1 = new GridLayoutManager(getBaseContext(), 1, GridLayoutManager.VERTICAL, false);
        recy_swap.setLayoutManager(manager1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_all_data, menu);
        return super.onCreateOptionsMenu(menu);
    }

    boolean active = true;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_swap){
            if(active){
                item.setIcon(R.drawable.icon_list);
                two.setVisibility(View.VISIBLE);
                one.setVisibility(View.GONE);
                active = false;
            }else {
                item.setIcon(R.drawable.icon_circled_menu);
                two.setVisibility(View.GONE);
                one.setVisibility(View.VISIBLE);
                active = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
