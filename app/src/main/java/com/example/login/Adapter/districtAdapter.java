package com.example.login.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.RecyclerViewInterface.HotHouseInterface;

import org.w3c.dom.Text;

public class districtAdapter extends RecyclerView.Adapter<districtAdapter.ViewHolder> {
    private final HotHouseInterface districtInterface;
    Context context;
    String[] district;
    int[] imageId;

    public districtAdapter(Context context, String[] district, int[] imageId, HotHouseInterface districtInterface) {
        this.context = context;
        this.district = district;
        this.imageId = imageId;
        this.districtInterface = districtInterface;
    }

    @NonNull
    @Override
    public districtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.district_card, parent, false), districtInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull districtAdapter.ViewHolder holder, int position) {
        Log.d("Position:district", String.valueOf(position));
        holder.ib_district.setImageResource(imageId[position]);
        holder.ib_district.setMaxHeight(800);
        holder.ib_district.setMaxWidth(400);
        holder.tv_district.setText(district[position]);
    }

    @Override
    public int getItemCount() {
        return district.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton ib_district;
        TextView tv_district;
        public ViewHolder(@NonNull View itemView, HotHouseInterface districtInterface) {
            super(itemView);

            ib_district = itemView.findViewById(R.id.ib_district);
            tv_district = itemView.findViewById(R.id.tv_district);

            ib_district.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (districtInterface != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        String district_temp = district[getAdapterPosition()];
                        districtInterface.onItemClickDistrict(district_temp);
                    }
                }
            });

            tv_district.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (districtInterface != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        String district_temp = district[getAdapterPosition()];
                        districtInterface.onItemClickDistrict(district_temp);
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(districtInterface != null)
                    {
                        if(getAdapterPosition()!= RecyclerView.NO_POSITION)
                        {
                            String district_temp = district[getAdapterPosition()];
                            districtInterface.onItemClickDistrict(district_temp);
                        }
                    }
                }
            });
        }
    }
}
