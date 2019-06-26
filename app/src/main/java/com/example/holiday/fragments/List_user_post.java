package com.example.holiday.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.holiday.Adapter.Transfer_data;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_post_product.Adapter.Adapter_new_post;
import com.example.holiday.Product_discount.Adapter.Adapter_discount;
import com.example.holiday.R;

import java.util.ArrayList;

public class List_user_post extends Fragment {

    public List_user_post() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Transfer_data transfer_data = new Transfer_data();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_user_post,container,false);

        ArrayList<Item_Post> item_post = transfer_data.getItem();
        ArrayList<Item_Post> items = new ArrayList<>();
        items.clear();
        for (int i =0;i<item_post.size();i++){
            if(item_post.get(i).getId() == 02){
               items.add(item_post.get(i));
            }
        }

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Adapter_new_post adapter1 = new Adapter_new_post(getContext(),items);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);
  //      rv.setNestedScrollingEnabled(false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager1);
        rv.setAdapter(adapter1);

        return view;
    }
}
