package com.example.holiday.Buy_sell_rent.Buy.Category;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.holiday.Adapter.Adapter_for_buy;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.Class_item.Item_buy;
import com.example.holiday.R;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class Vehicles extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    Window window;
    TextView title,back;
    ArrayList<Item_Post>items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_and__tablet);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        title = (TextView)findViewById(R.id.toolbar_title);
        title.setText(getIntent().getStringExtra("title"));
        back = (TextView)findViewById(R.id.tv_back);
        back.setText(getIntent().getStringExtra("back"));
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        BannerSlider slider = (BannerSlider)findViewById(R.id.bannerSlider);
        List<Banner> banners=new ArrayList<>();
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.slider_buy_sell_rent));
        banners.add(new DrawableBanner(R.drawable.slider2));
        //add banner using image url
//        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg"));
//        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg"));
        slider.setBanners(banners);

        items = new ArrayList<Item_Post>();
        items = (ArrayList<Item_Post>)getIntent().getSerializableExtra("items");

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        Adapter_for_buy adapter1 = new Adapter_for_buy(getBaseContext(),items);
        RecyclerView recy_horizontal1 = (RecyclerView) findViewById(R.id.recyclerview);
        recy_horizontal1.setHasFixedSize(true);
        recy_horizontal1.setLayoutManager(layoutManager1);
        recy_horizontal1.setAdapter(adapter1);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
