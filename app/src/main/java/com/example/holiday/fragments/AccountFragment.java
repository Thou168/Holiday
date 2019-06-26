package com.example.holiday.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.holiday.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {

    Button btn_upload;
    private Uri Imageuri;
    ImageView imageView;
    CircleImageView circleImageView;
    ImageButton img_profile;
    Toolbar toolbar;
    Fragment fragment = null;

    private static ViewPager viewPager;
    private static TabLayout tabLayout;

    private int REQUEST_profile = 0, REQUEST_cover = 1;
    private Button btnSelect;
    private ImageView ivImage;
    private String userChoosenTask;

    public AccountFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);

       // tabLayout = (TabLayout) view.findViewById(R.id.tab);
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));


        view.findViewById(R.id.btn_edit_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();
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
