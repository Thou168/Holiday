package com.example.holiday.startup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.holiday.Edit_Account.Edit_account;
import com.example.holiday.R;
import com.example.holiday.Setting.Setting;
import com.example.holiday.fragments.AccountFragment;
import com.example.holiday.fragments.List_user_post;
import com.example.holiday.fragments.NotificationFragment;

public class User_Active extends AppCompatActivity {

    private static ViewPager viewPager;
    private static TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__active);

        AccountFragment.PagerAdapter pagerAdapter = new AccountFragment.PagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tab);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));


        findViewById(R.id.btn_edit_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Edit_account.class));
            }
        });
        findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Setting.class));
            }
        });


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
                    fragment = new List_user_post();
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
