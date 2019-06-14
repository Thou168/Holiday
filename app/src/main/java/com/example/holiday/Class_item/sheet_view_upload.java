package com.example.holiday.Class_item;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.New_Activity.Utility;
import com.example.holiday.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class sheet_view_upload extends BottomSheetDialogFragment implements View.OnClickListener {
    private int REQUEST_profile = 0, REQUEST_cover = 1;
    private String userChoosenTask;
    private Uri Imageuri;
    ImageView imageView;

    private int reportType;
    private TextView TakePhoto,AddPhoto;
    private ActionListener mActionListener;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_view_upload, container, false);

    //    imageView = (ImageView)view.findViewById(R.id.imgCover);

        TakePhoto = (TextView)view.findViewById(R.id.tvTake_Photo);
        AddPhoto  = (TextView)view.findViewById(R.id.tvAdd_photo);
//
//       TakePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mActionListener!=null){
//                    mActionListener.onTextViewClick(R.id.tvTake_Photo);
//                    dismiss();
//                }
//            }
//        });
//
//        AddPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mActionListener!=null){
//                    mActionListener.onTextViewClick(R.id.tvAdd_photo);
//                    dismiss();
//                }
//            }
//        });
        mActionListener =(ActionListener) getParentFragment();
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTake_Photo:
                mActionListener.onTextViewClick(R.id.tvTake_Photo);
                dismiss();
                break;
            case R.id.tvAdd_photo:
                mActionListener.onTextViewClick(R.id.tvAdd_photo);
                dismiss();
                break;

        }
    }


    public void setChooseReasonListener() {
        this.mActionListener = mActionListener;
    }

    public interface ActionListener{
        void onTextViewClick(int id);
    }

//    private void selectImage() {
//        final CharSequence[] items = {"Take Photo", "Choose from Library",
//                "Cancel"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Add Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                boolean result = Utility.checkPermission(getActivity());
//                if (items[item].equals("Take Photo")) {
//                    userChoosenTask = "Take Photo";
//                    if (result)
//                        cameraIntent();
//                } else if (items[item].equals("Choose from Library")) {
//                    userChoosenTask = "Choose from Library";
//                    if (result)
//                        galleryIntent();
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    private void cameraIntent() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQUEST_cover);
//    }
//
//    private void galleryIntent() {
////        Intent intent = new Intent();
////        intent.setType("image/*");
////        intent.setAction(Intent.ACTION_GET_CONTENT);//
////        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
//        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(i, REQUEST_cover);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_cover && resultCode == RESULT_OK && null != data) {
//            Imageuri = data.getData();
//            Picasso.with(getContext()).load(Imageuri).into(imageView);
//        }
//        if (requestCode == REQUEST_profile && resultCode == RESULT_OK && null != data) {
//            Imageuri = data.getData();
//            CircleImageView circleImageView = (CircleImageView) getView().findViewById(R.id.imgProfile);
//            Picasso.with(getActivity()).load(Imageuri).into(circleImageView);
//        }
//    }
}