package com.example.holiday.New_post_product.Adapter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_post_product.Detail_new_product;
import com.example.holiday.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gjz on 17/06/2019.
 */

public class Adapter_list_gird extends RecyclerView.Adapter<Adapter_list_gird.ItemViewHolder>{
    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_TWO = 2;

    private static final int TYPE_LIST = 1;
    private static final int TYPE_GRID = 2;
    private static final int TYPE_IMAGE = 3;

    private List<Item_Post> mItems;
    private GridLayoutManager mLayoutManager;

    public Adapter_list_gird(List<Item_Post> items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;

    }


    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        boolean st = mLayoutManager.getStackFromEnd();
//        if (spanCount == SPAN_COUNT_ONE) {
//            return TYPE_LIST;
//        } else {
//            return TYPE_GRID;
//        }

        if (spanCount == SPAN_COUNT_ONE) {
            return TYPE_LIST;
        } else if (spanCount == SPAN_COUNT_TWO){
            return TYPE_GRID;
        }else{
            return TYPE_IMAGE;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        } else if(viewType == TYPE_GRID){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Item_Post item = mItems.get(position);
        holder.title.setText(item.getTitle());
        holder.iv.setImageResource(item.getImage());
        holder.price.setText("$ "+Double.toString(item.getCast()));
        holder.type_post.setText(item.getPost_type());
        if (getItemViewType(position) == TYPE_GRID) {
            holder.name.setText(item.getName());
            holder.img_user.setImageResource(item.getUser_image());
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Detail_new_product.class);
                intent.putExtra("img_user",item.getUser_image());
                intent.putExtra("name",item.getName());
                intent.putExtra("image",item.getImage());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("cost",item.getCast());
                intent.putExtra("color",item.getColor());
                intent.putExtra("brand",item.getBrand());
                intent.putExtra("year",item.getYear());
                intent.putExtra("condition",item.getCondition());
                intent.putExtra("text",item.getText());
                intent.putExtra("phone",item.getPhone());
                intent.putExtra("email",item.getEmail());
                intent.putExtra("type_post",item.getPost_type());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title;
        TextView name;
        CircleImageView img_user;
        TextView type_post;
        TextView price;
        LinearLayout linearLayout;

        public ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            price = itemView.findViewById(R.id.tv_price);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            type_post = (TextView) itemView.findViewById(R.id.type_post);
            if (viewType == TYPE_LIST) {
                iv = (ImageView) itemView.findViewById(R.id.img_view);
                title = (TextView) itemView.findViewById(R.id.tv_title);
//                info = (TextView) itemView.findViewById(R.id.tv_info);
            } else {
                iv = (ImageView) itemView.findViewById(R.id.img_view);
                title = (TextView) itemView.findViewById(R.id.tv_title);
                name = (TextView) itemView.findViewById(R.id.name_user);
                img_user = (CircleImageView) itemView.findViewById(R.id.img_user);

            }
        }
    }
}
