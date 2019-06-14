package com.example.holiday.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.holiday.Buy_sell_rent.Buy.Buy;
import com.example.holiday.Buy_sell_rent.Rent.Rent;
import com.example.holiday.Buy_sell_rent.Sell.Sell;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_Activity.Activity_Like;
import com.example.holiday.New_Activity.Search;
import com.example.holiday.Product_discount.Discount_more_data;
import com.example.holiday.New_post_product.Adapter.Adapter_new_post;
import com.example.holiday.R;
import com.example.holiday.Product_discount.Adapter.Adapter_discount;
import com.example.holiday.startup.MainActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        NavigationView.OnNavigationItemSelectedListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout shimmerViewContainer;
    boolean aBoolean = true;
    private RelativeLayout rela_buy;
    private RecyclerView list_discount;
    public ArrayList<Item_Post> item_discount;
    public ArrayList<Item_Post> item_posts;
    DrawerLayout drawer;
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drawerlayout,container,false);

        shimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);

//slider
        BannerSlider slider = (BannerSlider) view.findViewById(R.id.bannerSlider);
        List<Banner> banners=new ArrayList<>();
       //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.slider1));
        banners.add(new DrawableBanner(R.drawable.slider2));
        banners.add(new DrawableBanner(R.drawable.slider3));
        banners.add(new DrawableBanner(R.drawable.slider4));
        banners.add(new DrawableBanner(R.drawable.slider5));
        banners.add(new DrawableBanner(R.drawable.slider6));
        //add banner using image url
//        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg"));
//        banners.add(new RemoteBanner("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg"));
        slider.setBanners(banners);


        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(" ");
        setHasOptionsMenu(true);
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
//        to change the color of the refresh indictor
//        swipeRefreshLayout.setColorScheme(getResources().getColor(R.color.colorHome),
//                getResources().getColor(R.color.yourcolor),
//                getResources().getColor(R.color.yourcolor),
//                getResources().getColor(R.color.yourcolor));
        if(aBoolean) {
            swipeRefreshLayout.setVisibility(View.GONE);
            showLoadingIndicator(true);
            if (shimmerViewContainer.getVisibility() == View.VISIBLE) {
                showLoadingIndicator(false);
            }
            swipeRefreshLayout.setRefreshing(false);
            aBoolean = false;
        }
        item_discount = new ArrayList<Item_Post>();
        Item_post();
        for (int i=0;i<item_posts.size();i++){
            if(item_posts.get(i).getType().equals("Discount")){
                item_discount.add(item_posts.get(i));
            }
        }
      //  LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        final Adapter_discount adapter = new Adapter_discount(getContext(),item_discount);
        RecyclerView recy_horizontal = (RecyclerView) view.findViewById(R.id.list_discount);
        recy_horizontal.setHasFixedSize(true);
        recy_horizontal.setNestedScrollingEnabled(false);
        recy_horizontal.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recy_horizontal.setLayoutManager(manager);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Adapter_new_post adapter1 = new Adapter_new_post(getContext(),item_posts);
        RecyclerView recy_horizontal1 = (RecyclerView) view.findViewById(R.id.list_new_post);
        recy_horizontal1.setHasFixedSize(true);
        recy_horizontal1.setLayoutManager(layoutManager1);
        recy_horizontal1.setAdapter(adapter1);

        view.findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Discount_more_data.class);
                intent.putExtra("items",item_discount);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.rela_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Buy.class);
                intent.putExtra("items",item_posts);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.rela_sell).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Sell.class);
                intent.putExtra("items",item_posts);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.rela_rent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Rent.class);
                intent.putExtra("items",item_posts);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Search.class);
                intent.putExtra("items",item_posts);
                startActivity(intent);
            }
        });

        return view;
    }
    public void Item_post(){
        item_posts = new ArrayList<>();
        item_posts.add(new Item_Post(02,R.drawable.home,R.drawable.thean,2007,"Thean","0962363929", "Rent","Post","Home",
                "New","I hame Rent ","Home","Villa",null,null, null,
                2400.0,null,null,"Thean168@gmail.com","st 1233","I have home Rent if you want to sell please call me","5","3","south","50"));
        item_posts.add(new Item_Post(02,R.drawable.camera1,R.drawable.thean,2000,"Thean","0962363929", "Buy","Post","Electronic",
                "New","I want to buy Camera","Camera","Sony","black",null, null,
                2400.0,null,null,"Thean168@gmail.com","st 1233","I want to buy camera if you want to sell please call me",null,null,null,null));
        item_posts.add(new Item_Post(01,R.drawable.iphonex,R.drawable.thean,2018,"Thean","0962363929", "Sell","Discount","Phone",
                "Used" ,"Nex new 99%","Phone","Apple","White",null, "09555555",
                1100.0,999.0,"null","samang168@gmail.com", "st 273","sell iphone xs max good 97% qarranty 3 month form USA color white + orange",null,null,null,null));
        item_posts.add(new Item_Post(01,R.drawable.image_nex,R.drawable.samang,2018,"samang","086595985", "Sell","Discount","Motor",
               "Used" ,"Nex new 99%","Motor","Honda","black","5886868", "09555555",
                2000.0,1500.0,"Month","samang168@gmail.com", "st 273","sell Honda zoomer x 017 have tax plate number good 97% qarranty 3 month form Japan + thai color white + orange",null,null,null,null));
        item_posts.add(new Item_Post(02,R.drawable.image_honda_dream,R.drawable.thean,2010,"Thean","0962363929", "Buy","Post","Motor",
                "New","Honda dream new 99%","Motor","Honda","black","5886868", "09555555",
                2400.0,1900.0,"Month","Thean168@gmail.com","st 1233","Mortor nov sart khmea ey yat",null,null,null,null));
        item_posts.add(new Item_Post(03,R.drawable.image_suzuki,R.drawable.thou,2007,"Thou","081208272", "Buy","Post","Motor",
                "Used","Suzuki new 99%","Motor","Honda","black","5886868", "09555555",
                3400.0,1700.0,"Month","Senthou168@gmail.com","st 1233","លក់ suzuki 017 នៅស្អាត់​ \nម៉ាស៊ីននៅខ្លាំង ធានាគ្រឿងហ្ស៊ីន",null,null,null,null));
        item_posts.add(new Item_Post(03,R.drawable.samsung_s10,R.drawable.thou,2018,"Thou","081208272", "Sell","Post","Phone",
                "Used","samsung new 99%","phone","Samsung","White","null", "null",
                1000.0,null,"null","Senthou168@gmail.com","st 1233","លក់ phone samsung s10 នៅស្អាត់​ \nម៉ាស៊ីននៅខ្លាំង ធានាគ្រឿងហ្ស៊ីន",null,null,null,null));
        item_posts.add(new Item_Post(03,R.drawable.nokia,R.drawable.samang,2018,"Samsung","086595985", "Sell","Post","Phone",
                "Used","samsung new 99%","phone","Nokia","White","null", "null",
                1000.0,null,null,"Samang168@gmail.com","st 1233","លក់ phone Nokia s10 នៅស្អាត់​ \nម៉ាស៊ីននៅខ្លាំង ធានាគ្រឿងហ្ស៊ីន",null,null,null,null));
        item_posts.add(new Item_Post(03,R.drawable.image_macbook,R.drawable.samang,2018,"Apple","086595985", "Buy","Post","Computer",
                "Used","samsung new 99%","phone","Nokia","White","null", "null",
                1000.0,null,"null","Samang168@gmail.com","st 1233","លក់ computer mac pro នៅស្អាត់​ \nម៉ាស៊ីននៅខ្លាំង ធានាគ្រឿងហ្ស៊ីន",null,null,null,null));
        item_posts.add(new Item_Post(03,R.drawable.image_macbook,R.drawable.samang,2018,"Apple","086595985", "Sell","Post","Computer",
                "Used","samsung new 99%","phone","Nokia","White","null", "null",
                1000.0,null,"null","Samang168@gmail.com","st 1233","លក់ computer mac pro នៅស្អាត់​ \nម៉ាស៊ីននៅខ្លាំង ធានាគ្រឿងហ្ស៊ីន",null,null,null,null));
        item_posts.add(new Item_Post(03,R.drawable.fan,R.drawable.thean,2008,"Thean","086595985", "Sell","Post","Electronic",
                "Used","fan new 99%","Electronic","sony","Black",null, null,
                100.0,null,null,"Thean168@gmail.com","st 1233","លក់ fan new នៅស្អាត់​ \nម៉ាស៊ីននៅខ្លាំង ធានាគ្រឿងហ្ស៊ីន",null,null,null,null));

    }

    @Override
    public void onRefresh() {
        showLoadingIndicator(true);
        swipeRefreshLayout.setVisibility(View.GONE);
        if (shimmerViewContainer.getVisibility() == View.VISIBLE) {
            showLoadingIndicator(false);
        }
        swipeRefreshLayout.setRefreshing(false);
    }
    public void showLoadingIndicator(boolean active) {
        if (active) {
            shimmerViewContainer.setVisibility(View.VISIBLE);
        //    shimmerViewContainer.startShimmerAnimation();
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
           //         shimmerViewContainer.stopShimmerAnimation();
                    shimmerViewContainer.setVisibility(View.GONE);
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                }
            }, 1000);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
          //  viewPager.setCurrentItem(4);
        } else if (id == R.id.nav_post) {

        } else if (id == R.id.nav_like) {
            startActivity(new Intent(getContext(), Activity_Like.class));

        } else if (id == R.id.nav_loan) {

        } else if (id == R.id.nav_order) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
