package com.example.holiday.New_Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.R;
import com.example.holiday.fragments.NotificationFragment;
import com.example.holiday.fragments.Buy_sell_rent.fragme_buy_sell_rent;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_post extends AppCompatActivity {

    Window window;
    ArrayList<Item_Post>items;
    private static ViewPager viewPager;
    private static TabLayout tabLayout;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorMessage));

        CircleImageView cv = (CircleImageView) findViewById(R.id.img_user);
        cv.setImageResource(getIntent().getIntExtra("img_user",0));
        TextView tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText(getIntent().getStringExtra("name"));
//        items = new ArrayList<Item_Post>();
//        items = (ArrayList<Item_Post>)getIntent().getSerializableExtra("items");
//        int id = getIntent().getIntExtra("id",0);
//
//        ArrayList<Item_Post> item = new ArrayList<>();
//        for(int i = 0;i<items.size();i++){
//            if(items.get(i).getId() == id){
//                item.add(items.get(i));
//            }
//        }

//        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
//        Vertical_adapter adapter1 = new Vertical_adapter(getBaseContext(),item);
//        RecyclerView recy_horizontal1 = (RecyclerView)findViewById(R.id.recyclerview);
//        recy_horizontal1.setHasFixedSize(true);
//        recy_horizontal1.setLayoutManager(layoutManager1);
//        recy_horizontal1.setAdapter(adapter1);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));


    }
    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new fragme_buy_sell_rent();
                    break;
                case 1:
//                title_toolbar.setText("Home");
                    fragment = new NotificationFragment();
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount() {
            return 2;
        }
    }

}
