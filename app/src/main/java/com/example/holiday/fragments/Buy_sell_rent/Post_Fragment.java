package com.example.holiday.fragments.Buy_sell_rent;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.holiday.R;
import com.example.holiday.fragments.MessageFragment;
import com.example.holiday.startup.MainActivity;

import java.util.Objects;

public class Post_Fragment extends Fragment {

    private static ViewPager viewPager;
    private static TabLayout tabLayout;
    private PagerAdapter pagerAdapter;
    private FragmentActivity myContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post,container,false);


//        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
      //  viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
     //   viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    //    tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()){
//                    case 0:
//                        viewPager.setCurrentItem(0);
//                        break;
//                    case 1:
//                        viewPager.setCurrentItem(1);
//                        break;
//                    case 2:
//                        viewPager.setCurrentItem(2);
//                        break;
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
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
                    fragment = new MessageFragment();
                    break;
                case 1:
                    fragment = new MessageFragment();
                    break;
                case 2:
                    fragment = new MessageFragment();
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount() {
            return 3;
        }
    }
}
