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
    public notificationAdapter(Context context, List<Booking> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public notificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_choose, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notificationAdapter.ViewHolder holder, int position) {
            String title_txt = "A Booking Request From " + AppDatabaseSingleton.getInstance(context).getUserDao().getUserNameById(list.get(position).getBooker_id());
            holder.title.setText(title_txt);

            String description = AppDatabaseSingleton.getInstance(context).getUserDao().getUserNameById(list.get(position).getBooker_id()) +
                    " sent you a request to view your property - " + AppDatabaseSingleton.getInstance(context).getHouseDao().getTitleById(list.get(position).getHouse_id())
                    +  " on " +  new SimpleDateFormat("yyyy-MM-dd").format(list.get(position).getDate()) +
                    ". You may contact him/her at " +  AppDatabaseSingleton.getInstance(context).getUserDao().getContactById(list.get(position).getBooker_id());
        holder.description.setText(description);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

            ImageButton booker_profile, yes, no;
            TextView title, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            booker_profile = itemView.findViewById(R.id.booker_profile_btn);
            yes = itemView.findViewById(R.id.booking_yes_btn);
            no = itemView.findViewById(R.id.booking_no_btn);

            title = itemView.findViewById(R.id.booking_title_tv);
            description = itemView.findViewById(R.id.booking_description_tv);
        }
    }
}
