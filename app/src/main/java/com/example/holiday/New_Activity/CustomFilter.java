package com.example.holiday.New_Activity;

import android.widget.Filter;

import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_post_product.Adapter.Adapter_new_post;

import java.util.ArrayList;

/**
 * Created by Hp on 3/17/2016.
 */
public class CustomFilter extends Filter {

    MyAdapter adapter;
    ArrayList<Item_Post> filterList;


    public CustomFilter(ArrayList<Item_Post> filterList, MyAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Item_Post> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getTitle().toUpperCase().contains(constraint) || filterList.get(i).getProduct_type().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

       adapter.items= (ArrayList<Item_Post>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
