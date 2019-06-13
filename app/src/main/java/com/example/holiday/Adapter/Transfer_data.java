package com.example.holiday.Adapter;

import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.Class_item.Item_horizontal;

import java.util.ArrayList;

public class Transfer_data {

    static ArrayList<Item_Post> item_like = new ArrayList<>();

    public void addyoulike(Item_Post itmes) {
        this.item_like.add(itmes);
    }

    public void removelike(int position) {
        this.item_like.remove(position);
    }

    public ArrayList<Item_Post> getItem_like() { return this.item_like;
    }

}