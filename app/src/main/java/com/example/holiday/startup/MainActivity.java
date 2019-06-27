package com.example.holiday.startup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.holiday.Class_item.Item_horizontal;
import com.example.holiday.Login_Register.UserAccount;
import com.example.holiday.New_Activity.Activity_Like;
import com.example.holiday.R;
import com.example.holiday.fragments.CameraFragment;
import com.example.holiday.fragments.HomeFragment;
import com.example.holiday.fragments.MessageFragment;
import com.example.holiday.fragments.AccountFragment;
import com.example.holiday.fragments.NotificationFragment;
import com.example.holiday.New_Activity.Search;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    List<Item_horizontal> items;
    private static ViewPager viewPager;
    private static TabLayout tabLayout;
    private PagerAdapter pagerAdapter;
    private Toolbar toolbar;
    TextView title_toolbar;
    MenuItem searchitem,languageitem;

    Fragment homeFragment;
    Fragment notificationFragment;
    Fragment cameraFragment;
    Fragment messageFragment;
    Fragment accountFragment;
    Window window;

    Locale myLocale;
    String currentLanguage = "en", currentLang;
    SharedPreferences prefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loanlocale();
        setContentView(R.layout.activity_main);
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(" ");
//        setSupportActionBar(toolbar);
//        title_toolbar = (TextView)findViewById(R.id.title_toolbar);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorMessage));

        prefer = getSharedPreferences("Register",MODE_PRIVATE);
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()){
//                    case 0:
//                        viewPager.setCurrentItem(0);
////                        searchitem.setVisible(true);
////                        languageitem.setVisible(true);
////                        title_toolbar.setText("Home");
////                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorHome));
////                        window.setStatusBarColor(getResources().getColor(R.color.colorHome));
//                        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorHome));
//                        break;
//                    case 1:
//                        viewPager.setCurrentItem(1);
////                        searchitem.setVisible(false);
////                        languageitem.setVisible(false);
////                        title_toolbar.setText("Notification");
////                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorNotification));
////                        window.setStatusBarColor(getResources().getColor(R.color.colorNotification));
//                        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorNotification));
//                        break;
//                    case 2:
//                        viewPager.setCurrentItem(2);
////                        searchitem.setVisible(false);
////                        languageitem.setVisible(false);
////                        title_toolbar.setText("Camera");
////                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorCamera));
////                        window.setStatusBarColor(getResources().getColor(R.color.colorCamera));
//                        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorCamera));
//                        break;
//                    case 3:
//                        viewPager.setCurrentItem(3);
////                        searchitem.setVisible(false);
////                        languageitem.setVisible(false);
////                        title_toolbar.setText("Message");
////                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorMessage));
////                        window.setStatusBarColor(getResources().getColor(R.color.colorMessage));
//                        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorMessage));
//                        break;
//                    case 4:
//                        viewPager.setCurrentItem(4);
////                        searchitem.setVisible(false);
////                        languageitem.setVisible(false);
////                        title_toolbar.setText("Account");
////                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccount));
////                        window.setStatusBarColor(getResources().getColor(R.color.colorAccount));
//                        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorAccount));
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        cameraFragment = new CameraFragment();
        messageFragment = new MessageFragment();
        accountFragment = new AccountFragment();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        searchitem = menu.findItem(R.id.action_search);
        languageitem = menu.findItem(R.id.action_language);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            startActivity(new Intent(MainActivity.this, Search.class));
            return true;
        }
        if (id == R.id.action_language) {
            showChangeLanguage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private  void  showChangeLanguage(){
        final String[] list = {"English","Khmer"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language....");
        mBuilder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    setLocale("en");
                    recreate();
                }else if (which == 1){
                    setLocale("km");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mdialog = mBuilder.create();
        mBuilder.show();
    }
    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration confi = new Configuration();
        confi.locale = locale;
        getBaseContext().getResources().updateConfiguration(confi,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loanlocale(){
        SharedPreferences prefer = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefer.getString("My_Lang","");
        setLocale(language);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_profile) {
//            // Handle the camera action
//            viewPager.setCurrentItem(4);
//        } else if (id == R.id.nav_post) {
//
//        } else if (id == R.id.nav_like) {
//            startActivity(new Intent(MainActivity.this, Activity_Like.class));
//
//        } else if (id == R.id.nav_loan) {
//
//        } else if (id == R.id.nav_order) {
//
//        }
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = homeFragment;
                break;
            case 1:
//                title_toolbar.setText("Home");
                fragment = notificationFragment;
                break;
            case 2:
                fragment = cameraFragment;
                break;
            case 3:
                fragment = messageFragment;
                break;
            case 4:
                if (prefer.contains("token")|| prefer.contains("id")){
                   fragment = accountFragment;
                }else {
                    fragment = accountFragment;
                    Intent intent = new Intent(MainActivity.this, UserAccount.class);
                    startActivity(intent);
                }
              //  fragment = accountFragment;
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
