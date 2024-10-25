package com.ntt.mediatn;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ntt.mediatn.Adapter.FollowAdapter;
import com.ntt.mediatn.Adapter.FriendAdapter;
import com.ntt.mediatn.Model.FollowModel;
import com.ntt.mediatn.Model.FriendsModel;

import java.util.ArrayList;

public class FollowActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<FollowModel> list;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);

        recyclerView = findViewById(R.id.followRv);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        list = new ArrayList<>();

        FollowAdapter adapter = new FollowAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        database.getReference().child("Users")
                .child(auth.getUid())
                .child("follower").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            FollowModel followModel = dataSnapshot.getValue(FollowModel.class);
                            list.add(followModel);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}