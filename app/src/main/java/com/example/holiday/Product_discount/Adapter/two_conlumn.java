package com.example.holiday.Product_discount.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.Adapter.Transfer_data;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.Product_discount.Detail_pro_discount;
import com.example.holiday.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class two_conlumn extends RecyclerView.Adapter<two_conlumn.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item_Post> items;

    public two_conlumn(Context context, ArrayList<Item_Post> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_discount_show_all, viewGroup , false);

        return new ViewHolder(view);
    }
    Transfer_data transfer_data = new Transfer_data();

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder view, int i) {
        final Item_Post item = items.get(i);
        view.imageView.setImageResource(item.getImage());
        view.image_user.setImageResource(item.getUser_image());
        view.name_user.setText(item.getName());
        view.price.setText(Double.toString(item.getPrice()));
        view.o_price.setText(Double.toString(item.getCast()));
        view.o_price.setPaintFlags(view.o_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
    //    view.time.setText(item.getTime());
        view.title.setText(item.getTitle());
        view.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer_data.addyoulike(item);
                view.imageButton.setImageResource(R.drawable.icon_heart_dack);
                notifyDataSetChanged();
                Toast.makeText(context, "Item added to like.", Toast.LENGTH_SHORT).show();
            }
        });
        view.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_pro_discount.class);
                intent.putExtra("img_user",item.getUser_image());
                intent.putExtra("name",item.getName());
                intent.putExtra("image",item.getImage());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("cost",item.getCast());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("color",item.getColor());
                intent.putExtra("brand",item.getBrand());
                intent.putExtra("year",item.getYear());
                intent.putExtra("condition",item.getCondition());
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
        ImageButton imageButton;
        TextView name_user;
        TextView price,o_price;
        TextView time;
        TextView title;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_user = itemView.findViewById(R.id.img_user);
            imageView = itemView.findViewById(R.id.img_view);
            imageButton = itemView.findViewById(R.id.image_like);
            name_user = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
            time = itemView.findViewById(R.id.location);
            title = itemView.findViewById(R.id.tv_title);
            o_price = itemView.findViewById(R.id.tv_price1);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
