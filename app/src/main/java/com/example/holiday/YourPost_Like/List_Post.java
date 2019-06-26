package com.example.holiday.YourPost_Like;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.holiday.Adapter.Transfer_data;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_post_product.Adapter.Adapter_new_post;
import com.example.holiday.R;

import java.util.ArrayList;
import java.util.List;

public class List_Post extends AppCompatActivity {

    RecyclerView rv;
    Transfer_data transfer_data = new Transfer_data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__post);

        ArrayList<Item_Post> item_post = transfer_data.getItem();
        ArrayList<Item_Post> items = new ArrayList<>();
        for (int i =0;i<item_post.size();i++){
            if(item_post.get(i).getId() == 02){
                items.add(item_post.get(i));
            }
        }
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(List_Post.this, LinearLayoutManager.VERTICAL, false);
        Adapter_new_post adapter1 = new Adapter_new_post(List_Post.this,items);
        rv = (RecyclerView)findViewById(R.id.recyclerview);
        //      rv.setNestedScrollingEnabled(false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager1);
        rv.setAdapter(adapter1);

        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
