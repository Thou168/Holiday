package com.example.holiday.New_Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.holiday.Adapter.Transfer_data;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.Class_item.Item_horizontal;
import com.example.holiday.R;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class Activity_Like extends AppCompatActivity {

    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.blue_light));

        Transfer_data transfer_data = new Transfer_data();
        ArrayList<Item_Post> item_like = transfer_data.getItem_like();
//
//        Vertical_adapter adapter = new Vertical_adapter(getBaseContext(),item_like);
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        RecyclerView.LayoutManager recylerViewLayoutManager = new LinearLayoutManager(getBaseContext());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
//        GridLayoutManager manager = new GridLayoutManager(getBaseContext(), 1, GridLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);
    }
}
