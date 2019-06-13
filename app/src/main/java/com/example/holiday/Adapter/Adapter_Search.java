package com.example.holiday.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.holiday.Class_item.Item_horizontal;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Search extends RecyclerView.Adapter<Adapter_Search.SearchViewHolder> implements Filterable {

    private List<Item_horizontal> exlist;
    private List<Item_horizontal> listfull;

    public Adapter_Search(List<Item_horizontal> exlist) {
        this.exlist = exlist;
        listfull = new ArrayList<>(exlist);
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }
    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Item_horizontal> filteredList = new ArrayList<>();
            if(constraint == null||constraint.length()==0){
                filteredList.addAll(listfull);
            }else {
                String filterpattern = constraint.toString().toLowerCase().trim();
                for(Item_horizontal item : listfull){
                    if(item.getTitle().toLowerCase().contains(filterpattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exlist.clear();
            exlist.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView price;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
