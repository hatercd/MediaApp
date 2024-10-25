package com.ntt.mediatn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntt.mediatn.Model.Post;
import com.ntt.mediatn.R;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.viewHolder>{

    ArrayList<Post> list;
    Context context;

    public DashboardAdapter(ArrayList<Post> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_dashboard, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Post model =list.get(position);

//        holder.profile.setImageResource(model.getProfile());
//        holder.postImage.setImageResource(model.getPostImage());
//        holder.name.setText(model.getName());
//        holder.about.setText(model.getAbout());
//        holder.like.setText(model.getLike());
//        holder.comment.setText(model.getComment());
//        holder.share.setText(model.getShare());

        holder.postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile, postImage, save;
        TextView name, about, like, comment, share;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile =itemView.findViewById(R.id.profile_image);
            postImage =itemView.findViewById(R.id.postImg);
            save =itemView.findViewById(R.id.save);
            name =itemView.findViewById(R.id.userName);
            about =itemView.findViewById(R.id.about);
            like =itemView.findViewById(R.id.like);
            comment =itemView.findViewById(R.id.comment);
            share =itemView.findViewById(R.id.share);
        }
    }
}
