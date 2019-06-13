package com.example.holiday.Buy_sell_rent.Rent.Adapter;

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

import com.example.holiday.Buy_sell_rent.Rent.Detail_product.Detail_Home;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.New_post_product.Detail_new_product;
import com.example.holiday.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item_Post> items;

    public Home_Adapter(Context context, ArrayList<Item_Post> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_new_post, viewGroup , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder view, int i) {
        final Item_Post item = items.get(i);
        view.imageView.setImageResource(item.getImage());
//        view.image_user.setImageResource(item.getImage_user());
//        view.name_user.setText(item.getName_user());
        view.price.setText(Double.toString(item.getCast()));
      //  view.time.setText(item.getTime());
        view.title.setText(item.getTitle());
        view.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Home.class);
                intent.putExtra("img_user",item.getUser_image());
                intent.putExtra("name",item.getName());
                intent.putExtra("image",item.getImage());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("cost",item.getCast());
                intent.putExtra("bedroom",item.getBedroom());
                intent.putExtra("bathroom",item.getBathroom());
                intent.putExtra("facing",item.getFacing());
                intent.putExtra("size",item.getSize());
                intent.putExtra("text",item.getText());
                intent.putExtra("phone",item.getPhone());
                intent.putExtra("email",item.getEmail());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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
            price = itemView.findViewById(R.id.tv_price);
            time = itemView.findViewById(R.id.location);
            title = itemView.findViewById(R.id.tv_title);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
