package com.example.login.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import org.w3c.dom.Text;

public class districtAdapter extends RecyclerView.Adapter<districtAdapter.ViewHolder> {
    Context context;
    String[] district;
    int[] imageId;

    public districtAdapter(Context context, String[] district, int[] imageId) {
        this.context = context;
        this.district = district;
        this.imageId = imageId;
    }

    @NonNull
    @Override
    public districtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.district_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull districtAdapter.ViewHolder holder, int position) {
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ib_district = itemView.findViewById(R.id.ib_district);
            tv_district = itemView.findViewById(R.id.tv_district);
        }
    }
}
