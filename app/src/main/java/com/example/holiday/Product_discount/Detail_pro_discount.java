package com.example.holiday.Product_discount;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.holiday.Buy_sell_rent.Sell.Detail_product.Detail_phone;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_Activity.User_post;
import com.example.holiday.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class Detail_pro_discount extends AppCompatActivity {

    Button btn_call,phone1,phone2,cancel;
    BottomSheetDialog bottomSheetDialog;
    TextView tv_dis,tv_title,name,price,brand,year,color,condition,prices,discount,text,phone,email;
    ArrayList<Item_Post>items;
    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final CircleImageView circleImageView = (CircleImageView) findViewById(R.id.cr_img);
        circleImageView.setImageResource(getIntent().getIntExtra("img_user",R.drawable.thou));


        BannerSlider slider = (BannerSlider)findViewById(R.id.image);
        List<Banner> banners=new ArrayList<>();
        //add banner using resource drawable
        banners.add(new DrawableBanner(getIntent().getIntExtra("image",R.drawable.slider2)));
        //add banner using image url
        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg"));
        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg"));
        slider.setBanners(banners);

        tv_dis = (TextView)findViewById(R.id.tv_price1);
        tv_dis.setText(Double.toString(getIntent().getDoubleExtra("cost",0)));
        tv_dis.setPaintFlags(tv_dis.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        tv_title = (TextView)findViewById(R.id.title);
        tv_title.setText(getIntent().getStringExtra("title"));
        name = (TextView)findViewById(R.id.tv_name);
        name.setText(getIntent().getStringExtra("name"));
        price = (TextView)findViewById(R.id.tv_cost);
        price.setText(Double.toString(getIntent().getDoubleExtra("price",0)));
        brand = (TextView)findViewById(R.id.tv_year);
        brand.setText(getIntent().getStringExtra("brand"));
        year = (TextView)findViewById(R.id.tv_color);
        year.setText(Integer.toString(getIntent().getIntExtra("year",0)));
        color = (TextView)findViewById(R.id.tv_condition);
        color.setText(getIntent().getStringExtra("color"));
        condition = (TextView)findViewById(R.id.tv_price);
        condition.setText(getIntent().getStringExtra("condition"));
        prices = (TextView)findViewById(R.id.tv_prices);
        prices.setText(Double.toString(getIntent().getDoubleExtra("price",0)));
        text = (TextView)findViewById(R.id.text);
        text.setText(getIntent().getStringExtra("text"));
        phone = (TextView)findViewById(R.id.tv_phone);
        phone.setText(getIntent().getStringExtra("phone"));
        email = (TextView)findViewById(R.id.tv_email);
        email.setText(getIntent().getStringExtra("email"));

        findViewById(R.id.cr_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),User_post.class);
                intent.putExtra("img_user",getIntent().getIntExtra("img_user",R.drawable.thou));
                intent.putExtra("name",name.getText());
                startActivity(intent);
            }
        });

        btn_call = (Button)findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomsheet(v);
                phone1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String num = phone1.getText().toString();
                        makePhonecall(num);
                        bottomSheetDialog.dismiss();
                    }
                });
                phone2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String num = phone2.getText().toString();
                        makePhonecall(num);
                        bottomSheetDialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
    }
    public void bottomsheet(View v){
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.bottom_sheet_call,null);
        phone1 = view.findViewById(R.id.btnPhone1);
        phone2 = view.findViewById(R.id.btnPhone2);
        cancel = view.findViewById(R.id.btnCancel);

        phone1.setText(getIntent().getStringExtra("phone"));

        bottomSheetDialog = new BottomSheetDialog(v.getContext());
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bottomSheetDialog.show();
    }
    public void makePhonecall(String num){

        if (ContextCompat.checkSelfPermission(Detail_pro_discount.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Detail_pro_discount.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ num)));
        }
    }

}
