package com.example.holiday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.holiday.Class_item.Item_horizontal;
import com.example.holiday.New_Activity.Detail_new_post;
import com.example.holiday.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vertical_adapter1 extends RecyclerView.Adapter<Vertical_adapter1.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item_horizontal> items;

    public Vertical_adapter1(Context context, ArrayList<Item_horizontal> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_list, viewGroup , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder view, int i) {
       final Item_horizontal item = items.get(i);
        view.imageView.setImageResource(item.getImage());
//        view.image_user.setImageResource(item.getImage_user());
//        view.name_user.setText(item.getName_user());
//        view.price.setText(Double.toString(item.getPrice()));
//        view.time.setText(item.getTime());
//        view.title.setText(item.getTitle());
        view.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Detail_new_post.class);
              //  Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image_user;
        ImageView imageView;
        TextView name_user;
        TextView price;
        TextView time;
        TextView title;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image_user = itemView.findViewById(R.id.img_user);
            imageView = itemView.findViewById(R.id.img_view);
//            name_user = itemView.findViewById(R.id.user);
//            price = itemView.findViewById(R.id.tv_price);
//            time = itemView.findViewById(R.id.tv_time);
//            title = itemView.findViewById(R.id.tv_title);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
