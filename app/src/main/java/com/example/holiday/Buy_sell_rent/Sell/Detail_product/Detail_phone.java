package com.example.holiday.Buy_sell_rent.Sell.Detail_product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.holiday.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class Detail_phone extends AppCompatActivity {

    TextView name,tv_title,price,prices,brand,year,color,condition,text,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.cr_img);
        circleImageView.setImageResource(getIntent().getIntExtra("img_user",R.drawable.thou));
        BannerSlider slider = (BannerSlider)findViewById(R.id.image);
        List<Banner> banners=new ArrayList<>();
        //add banner using resource drawable
        banners.add(new DrawableBanner(getIntent().getIntExtra("image",R.drawable.slider1)));
        //add banner using image url
        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg"));
        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg"));
        slider.setBanners(banners);

        tv_title = (TextView)findViewById(R.id.title);
        tv_title.setText(getIntent().getStringExtra("title"));
        name = (TextView)findViewById(R.id.tv_name);
        name.setText(getIntent().getStringExtra("name"));
        price = (TextView)findViewById(R.id.tv_price);
        price.setText(Double.toString(getIntent().getDoubleExtra("cost",0)));
        brand = (TextView)findViewById(R.id.tv_brand);
        brand.setText(getIntent().getStringExtra("brand"));
        year = (TextView)findViewById(R.id.tv_year);
        year.setText(Integer.toString(getIntent().getIntExtra("year",0)));
        color = (TextView)findViewById(R.id.tv_color);
        color.setText(getIntent().getStringExtra("color"));
        condition = (TextView)findViewById(R.id.tv_condition);
        condition.setText(getIntent().getStringExtra("condition"));
        prices = (TextView)findViewById(R.id.tv_prices);
        prices.setText(Double.toString(getIntent().getDoubleExtra("cost",0)));
        text = (TextView)findViewById(R.id.text);
        text.setText(getIntent().getStringExtra("text"));
        phone = (TextView)findViewById(R.id.tv_phone);
        phone.setText(getIntent().getStringExtra("phone"));
        email = (TextView)findViewById(R.id.tv_email);
        email.setText(getIntent().getStringExtra("email"));
    }
}
