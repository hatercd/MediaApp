package com.ntt.mediatn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntt.mediatn.Model.FollowModel;
import com.ntt.mediatn.Model.User;
import com.ntt.mediatn.R;
import com.ntt.mediatn.databinding.RvFollowBinding;
import com.squareup.picasso.Picasso;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.viewHolder>{
    ArrayList<FollowModel> list;
    Context context;

    public FollowAdapter(ArrayList<FollowModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_follow, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        FollowModel followModel = list.get(position);

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(followModel.getFollowedBy()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Picasso.get()
                                .load(user.getProfile())
                                .placeholder(R.drawable.placeholder)
                                .into(holder.binding.profileImage);
                        holder.binding.name.setText(user.getName());
                        holder.binding.job.setText(user.getJob());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        RvFollowBinding binding;

//        ImageView profile;
//        TextView name, job;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = RvFollowBinding.bind(itemView);

//            profile = itemView.findViewById(R.id.profile_image);
//            name = itemView.findViewById(R.id.name);
//            job = itemView.findViewById(R.id.job);
        }
    }
}
