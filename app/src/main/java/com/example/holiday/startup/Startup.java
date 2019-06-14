package com.example.holiday.startup;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.holiday.R;
import com.example.holiday.fragments.AccountFragment;
import com.example.holiday.fragments.CameraFragment;
import com.example.holiday.fragments.Buy_sell_rent.Post_Fragment;
import com.example.holiday.fragments.MessageFragment;

public class Startup extends AppCompatActivity {

    private static ViewPager viewPager;
    private static TabLayout tabLayout;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

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
//        title_toolbar.setText(null);
            switch (position){
                case 0:
                    fragment = new Post_Fragment();
                    break;
                case 1:
//                  title_toolbar.setText("Home");
                    fragment = new CameraFragment();
                    break;
                case 2:
                    fragment = new MessageFragment();
                    break;
                case 3:
                    fragment = new AccountFragment();
                    break;
                case 4:
                    fragment = new AccountFragment();
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount() {
            return 5;
        }
    }
}
