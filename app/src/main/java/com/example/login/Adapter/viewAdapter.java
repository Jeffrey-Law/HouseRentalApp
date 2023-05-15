package com.example.login.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.House;
import com.example.login.R;
import com.example.login.RecyclerViewInterface.ViewInterface;

import java.util.List;

public class viewAdapter extends RecyclerView.Adapter<viewAdapter.ViewHolder> {
    private final ViewInterface viewInterface;
    Context context;
    List<House> list;

    public viewAdapter(Context context, List<House> list, ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.house_card2, parent, false), viewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull viewAdapter.ViewHolder holder, int position) {
        holder.iv_house_image.setImageBitmap(list.get(position).getHouse_image());
        holder.tv_house_name.setText(list.get(position).getTitle()); // TODO: Change to getHouse name
        holder.tv_house_price.setText("$" + list.get(position).getPrice() + "/day");
        holder.tv_house_district.setText(list.get(position).getDistrict());

        int rating = list.get(position).getRating();
        switch (rating) {
            case 0:
                holder.star1.setImageResource(0); // Clear the image resource
                holder.star2.setImageResource(0);
                holder.star3.setImageResource(0);
                holder.star4.setImageResource(0);
                holder.star5.setImageResource(0);
                break;
            case 1:
                holder.star1.setImageResource(R.drawable.star);
                holder.star2.setImageResource(0);
                holder.star3.setImageResource(0);
                holder.star4.setImageResource(0);
                holder.star5.setImageResource(0);
                break;
            case 2:
                holder.star1.setImageResource(R.drawable.star);
                holder.star2.setImageResource(R.drawable.star);
                holder.star3.setImageResource(0);
                holder.star4.setImageResource(0);
                holder.star5.setImageResource(0);
                break;
            case 3:
                holder.star1.setImageResource(R.drawable.star);
                holder.star2.setImageResource(R.drawable.star);
                holder.star3.setImageResource(R.drawable.star);
                holder.star4.setImageResource(0);
                holder.star5.setImageResource(0);
                break;
            case 4:
                holder.star1.setImageResource(R.drawable.star);
                holder.star2.setImageResource(R.drawable.star);
                holder.star3.setImageResource(R.drawable.star);
                holder.star4.setImageResource(R.drawable.star);
                holder.star5.setImageResource(0);
                break;
            case 5:
                holder.star1.setImageResource(R.drawable.star);
                holder.star2.setImageResource(R.drawable.star);
                holder.star3.setImageResource(R.drawable.star);
                holder.star4.setImageResource(R.drawable.star);
                holder.star5.setImageResource(R.drawable.star);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_house_image, star1, star2, star3, star4, star5;
        TextView tv_house_name, tv_house_price, tv_house_district;

        public ViewHolder(@NonNull View itemView, ViewInterface viewInterface) {
            super(itemView);

            iv_house_image = itemView.findViewById(R.id.iv_house_image);
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
            star4 = itemView.findViewById(R.id.star4);
            star5 = itemView.findViewById(R.id.star5);
            tv_house_name = itemView.findViewById(R.id.tv_house_name);
            tv_house_price = itemView.findViewById(R.id.tv_house_price);
            tv_house_district = itemView.findViewById(R.id.tv_house_district);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewInterface != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            int id = list.get(getAdapterPosition()).getHouse_id();
                            viewInterface.onItemClickFavourite(id);
                        }
                    }
                }
            });
        }
    }
}
