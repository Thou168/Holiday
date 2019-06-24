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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holiday.Adapter.Transfer_data;
import com.example.holiday.Class_item.Item_Post;
import com.example.holiday.Product_discount.Detail_pro_discount;
import com.example.holiday.New_Activity.User_post;
import com.example.holiday.R;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_discount extends RecyclerView.Adapter<Adapter_discount.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item_Post> items;

    public Adapter_discount(Context context, ArrayList<Item_Post> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_discount, viewGroup , false);

        return new ViewHolder(view);
    }

//    ArrayList<Item_Post>item_post= new ArrayList<>();
    Transfer_data transfer_data = new Transfer_data();
    boolean one=true;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder view, final int i) {
        final Item_Post item = items.get(i);

        view.imageView.setImageResource(item.getImage());
        view.image_user.setImageResource(item.getUser_image());
        view.name_user.setText(item.getName());
        view.price.setText(Double.toString(item.getCast()));
        view.o_price.setText(Double.toString(item.getCast()));
        view.o_price.setPaintFlags(view.o_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
    //    view.time.setText(item.getTime());
        view.title.setText(item.getTitle());
        view.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (one) {
                        transfer_data.addyoulike(item);
                        view.imageButton.setImageResource(R.drawable.icon_heart_dack);
                        notifyDataSetChanged();
                        one = false;
                        Toast.makeText(context, "Item added to like.", Toast.LENGTH_SHORT).show();
                    }else{
                        view.imageButton.setImageResource(R.drawable.icon_heart);
                        Toast.makeText(context, "Item remove.", Toast.LENGTH_SHORT).show();
                        one = true;
                    }
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
                context.startActivity(intent);
            }
        });

        view.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), User_post.class);
                intent.putExtra("img_user",item.getUser_image());
                intent.putExtra("name",item.getName());
                intent.putExtra("items",items);
                intent.putExtra("id",item.getId());
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
        ImageButton imageButton;
        TextView name_user;
        TextView price,o_price;
        TextView time,phone;
        TextView title;
        CircleImageView circleImageView;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_user = itemView.findViewById(R.id.img_user);
            imageView = itemView.findViewById(R.id.img_view);
            imageButton = itemView.findViewById(R.id.image_like);
            name_user = itemView.findViewById(R.id.name_user);
            price = itemView.findViewById(R.id.tv_price);
//            time = itemView.findViewById(R.id.location);
            title = itemView.findViewById(R.id.tv_title);
            o_price = itemView.findViewById(R.id.tv_price1);
//            phone = itemView.findViewById(R.id.tv_iconphone);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            relativeLayout = itemView.findViewById(R.id.relative);
        }
    }
}
