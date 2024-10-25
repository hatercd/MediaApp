package com.ntt.mediatn.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntt.mediatn.Model.ActivityModel;
import com.ntt.mediatn.R;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.viewHolder>{

    ArrayList<ActivityModel> list;
    Context context;

    public ActivityAdapter(ArrayList<ActivityModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity2_rv,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ActivityModel model = list.get(position);

        holder.profile.setImageResource(model.getProfile());
        holder.activity.setText(Html.fromHtml(model.getActivity()));
        holder.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile;
        TextView activity, time;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_image);
            activity = itemView.findViewById(R.id.activity);
            time = itemView.findViewById(R.id.time);
        }
    }
}
