package com.example.holiday.fragments;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.holiday.Edit_Account.Edit_account;
import com.example.holiday.New_Activity.User_post;
import com.example.holiday.New_Activity.Utility;
import com.example.holiday.R;
import com.example.holiday.Setting.Setting;
import com.example.holiday.fragments.Buy_sell_rent.fragme_buy_sell_rent;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment {
    private Bitmap bitmap;

    private Uri Imageuri;

    Toolbar toolbar;
    Fragment fragment = null;

    private static ViewPager viewPager;
    private static TabLayout tabLayout;
    private ImageView Cover_Image;
    private CircleImageView Profile_Image;
    private Button btnUpload_cover;
    private int REQUEST_profile = 0, REQUEST_cover = 1;
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

        Cover_Image = (ImageView)view.findViewById(R.id.imgCover);
        Profile_Image = (CircleImageView)view.findViewById(R.id.imgProfile);
        btnUpload_cover = (Button)view.findViewById(R.id.btnUpload_Cover);
        btnUpload_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selectImage_Cover();
              }
        });

        Profile_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage_Profile();
            }
        });
        view.findViewById(R.id.btn_edit_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Edit_account.class));
            }
        });

        view.findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Setting.class));
            }
        });
        return view;

    }  // create
    private void selectImage_Profile() {
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
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_profile);


                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, REQUEST_profile);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void selectImage_Cover() {
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
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_cover);


                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, REQUEST_cover);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent()
    {

    }
    private void galleryIntent()
    {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);//
//        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_cover && resultCode == RESULT_OK && null != data) {
            Imageuri = data.getData();
            Picasso.with(getActivity()).load(Imageuri).into(Cover_Image);
        }
        if (requestCode == REQUEST_profile && resultCode == RESULT_OK && null != data) {
            Imageuri = data.getData();
            Picasso.with(getActivity()).load(Imageuri).into(Profile_Image);
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
