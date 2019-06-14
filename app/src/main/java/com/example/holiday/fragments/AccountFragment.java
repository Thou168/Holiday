package com.example.holiday.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.holiday.Class_item.sheet_view_upload;
import com.example.holiday.New_Activity.User_post;
import com.example.holiday.New_Activity.Utility;
import com.example.holiday.R;
import com.example.holiday.Setting.Setting;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment {

    private Button btn_upload;
    private Uri Imageuri;

    private CircleImageView circleImageView;
    private ImageButton img_profile;
    private Toolbar toolbar;
    private Fragment fragment = null;

    private static ViewPager viewPager;
    private static TabLayout tabLayout;

    private int REQUEST_profile = 0, REQUEST_cover = 1;
    private Button btnSelect;
    private ImageView ImageCover;
    private Button Upload_cover;
    private String userChoosenTask;
    private SharedPreferences prefer;

    public AccountFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account,container,false);

       // tabLayout = (TabLayout) view.findViewById(R.id.tab);
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        prefer = PreferenceManager.getDefaultSharedPreferences(getActivity());


        view.findViewById(R.id.btn_edit_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Setting.class));
            }
        });

        btn_upload = (Button)view.findViewById(R.id.btnUpload_Cover);
        view.findViewById(R.id.btnUpload_Cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageCover();
            }
        });

        view.findViewById(R.id.imgProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageProfile();
            }
        });
        return view;

    } //creatview;


    private void selectImageProfile() {

        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_profile);
                    }
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result){
                        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, REQUEST_profile);
                    }

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void selectImageCover() {

        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_cover);
                    }
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result){
                        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, REQUEST_cover);
                    }

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

//    private void cameraIntent()
//    {
//
//    }
//    private void galleryIntent()
//    {
////        Intent intent = new Intent();
////        intent.setType("image/*");
////        intent.setAction(Intent.ACTION_GET_CONTENT);//
////        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
//
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_cover && resultCode == RESULT_OK && null != data) {
            Imageuri = data.getData();
            ImageCover = (ImageView)getView().findViewById(R.id.imgCover) ;
            Picasso.with(getActivity()).load(Imageuri).into(ImageCover);
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("image", String.valueOf(Imageuri));
            editor.commit();
        }
        if (requestCode == REQUEST_profile && resultCode == RESULT_OK && null != data) {
            Imageuri = data.getData();
            circleImageView = (CircleImageView) getView().findViewById(R.id.imgProfile) ;
            Picasso.with(getActivity()).load(Imageuri).into(circleImageView);
            SharedPreferences preferences = getContext().getSharedPreferences("Image",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("image", String.valueOf(Imageuri));
            editor.commit();
        }
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
