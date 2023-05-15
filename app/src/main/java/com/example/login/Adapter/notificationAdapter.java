package com.example.login.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.AppDatabase;
import com.example.login.AppDatabaseSingleton;
import com.example.login.Booking;
import com.example.login.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

public class notificationAdapter extends RecyclerView.Adapter<notificationAdapter.ViewHolder> {
    Context context;
    List<Booking> list;
    int owner_id;
    public notificationAdapter(Context context, List<Booking> list) {
        this.context = context;
        this.list = list;
        if(list.size() != 0)
            this.owner_id = list.get(0).getHouse_owner_id();
        else
            this.owner_id = 0;
    }
    @NonNull
    @Override
    public notificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_result, parent, false);

        notificationAdapter.ViewHolder viewHolder = new notificationAdapter.ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull notificationAdapter.ViewHolder holder, int position) {

        Bitmap RV = AppDatabaseSingleton.getInstance(context).getUserDao().getUserById(list.get(position).getHouse_owner_id()).getUser_image();
        if(RV != null){
            holder.owner_profile.setImageBitmap(RV);
        }

    int status_int = list.get(position).getStatus();
    String description = "";
    switch (status_int){
        case 0:
             description = AppDatabaseSingleton.getInstance(context).getUserDao().getUserNameById(list.get(position).getHouse_owner_id()) +
                    " still viewing your request to view the property - " + AppDatabaseSingleton.getInstance(context).getHouseDao().getTitleById(list.get(position).getHouse_id())
                    +  " on " +  new SimpleDateFormat("yyyy-MM-dd").format(list.get(position).getDate()) +
                    ". You may contact him/her at " +  AppDatabaseSingleton.getInstance(context).getUserDao().getContactById(list.get(position).getHouse_owner_id());
            holder.description.setText(description);
            holder.status.setImageResource(R.drawable.question_mark);
            break;
        case 1:
            description = AppDatabaseSingleton.getInstance(context).getUserDao().getUserNameById(list.get(position).getHouse_owner_id()) +
                    " has accept your request to view the property - " + AppDatabaseSingleton.getInstance(context).getHouseDao().getTitleById(list.get(position).getHouse_id())
                    +  " on " +  new SimpleDateFormat("yyyy-MM-dd").format(list.get(position).getDate()) +
                    ". You may contact him/her at " +  AppDatabaseSingleton.getInstance(context).getUserDao().getContactById(list.get(position).getHouse_owner_id());
            holder.description.setText(description);
            holder.status.setImageResource(R.drawable.accept);
            break;
        case 2:
            description = AppDatabaseSingleton.getInstance(context).getUserDao().getUserNameById(list.get(position).getHouse_owner_id()) +
                    " has decline your request to view the property - " + AppDatabaseSingleton.getInstance(context).getHouseDao().getTitleById(list.get(position).getHouse_id())
                    +  " on " +  new SimpleDateFormat("yyyy-MM-dd").format(list.get(position).getDate());
            holder.description.setText(description);
            holder.status.setImageResource(R.drawable.decline);
    }

    String title_txt = "A Booking Response From " + AppDatabaseSingleton.getInstance(context).getUserDao().getUserNameById(list.get(position).getBooker_id());
    holder.title.setText(title_txt);

    Booking booking = AppDatabaseSingleton.getInstance(context).getBookingDao().getBookingById(list.get(position).getBooking_id());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView owner_profile, status;
        TextView title, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            owner_profile = itemView.findViewById(R.id.booker_response_profile_btn);
            status = itemView.findViewById(R.id.booking_response_status);
            title = itemView.findViewById(R.id.booking_response_title_tv);
            description = itemView.findViewById(R.id.booking_response_description_tv);
        }
    }


}
