package com.ntt.mediatn.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntt.mediatn.Model.FollowModel;
import com.ntt.mediatn.Model.User;
import com.ntt.mediatn.R;
import com.ntt.mediatn.databinding.RvSearchUserBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.viewHolder>{

    Context context;
    ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_search_user, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        User user = list.get(position);
        Picasso.get()
                .load(user.getProfile())
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.profileImage);
        holder.binding.name.setText(user.getName());
        holder.binding.job.setText(user.getJob());

        FirebaseDatabase.getInstance().getReference()
                        .child("Users")
                                .child(user.getUserID())
                                        .child("follower")
                                                .child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            holder.binding.followBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.shape_formload));
                            holder.binding.followBtn.setText("Đang theo dõi");
                            holder.binding.followBtn.setTextColor(ContextCompat.getColor(context, R.color.blue));
                            holder.binding.followBtn.setEnabled(false);
                        }
                        else {
                            holder.binding.followBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    FollowModel followModel = new FollowModel();
                                    followModel.setFollowedBy(FirebaseAuth.getInstance().getUid());
                                    followModel.setFollowedAt(new Date().getTime());

                                    FirebaseDatabase.getInstance().getReference()
                                            .child("Users")
                                            .child(user.getUserID())
                                            .child("follower")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .setValue(followModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    FirebaseDatabase.getInstance().getReference()
                                                            .child("Users")
                                                            .child(user.getUserID())
                                                            .child("followerCount")
                                                            .setValue(user.getFollowerCount() +1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    holder.binding.followBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.shape_formload));
                                                                    holder.binding.followBtn.setText("Đã theo dõi");
                                                                    holder.binding.followBtn.setTextColor(ContextCompat.getColor(context, R.color.blue));
                                                                    holder.binding.followBtn.setEnabled(false);
                                                                    Toast.makeText(context, "Đã theo dõi " +user.getName(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                }
                                            });
                                }
                            });
                        }
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
        RvSearchUserBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvSearchUserBinding.bind(itemView);
        }
    }
}
