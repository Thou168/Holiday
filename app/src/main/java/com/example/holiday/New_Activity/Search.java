package com.example.holiday.New_Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_post_product.Adapter.Adapter_new_post;
import com.example.holiday.R;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    SearchView sv;
    RecyclerView rv;
    ArrayList<Item_Post> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        handleIntent(getIntent());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sv= (SearchView) findViewById(R.id.mSearch);
        rv = (RecyclerView)findViewById(R.id.myRecycler) ;
        sv.setFocusable(true);
        sv.setIconified(false);
        sv.requestFocusFromTouch();

        items = new ArrayList<Item_Post>();
        items = (ArrayList<Item_Post>)getIntent().getSerializableExtra("items");

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        final MyAdapter adapter1 = new MyAdapter(getBaseContext(),items);
//        RecyclerView recy_horizontal1 = (RecyclerView) view.findViewById(R.id.list_new_post);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager1);
        rv.setAdapter(adapter1);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter1.getFilter().filter(query);
                return false;
            }
        });

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        // Inflate menu to add items to action bar if it is present.
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem search_item = menu.findItem(R.id.action_search);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setFocusable(true);
//        searchView.setQueryHint("Search for Products, Brands and More");
//        search_item.expandActionView();
//
//        return true;
//    }
//    @Override
//    protected void onNewIntent(Intent intent) {
//        handleIntent(intent);
//    }
//
//    private void handleIntent(Intent intent) {
//
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            //use the query to search your data somehow
//        }
//    }
}
