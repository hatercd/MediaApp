package com.ntt.mediatn.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ntt.mediatn.Adapter.FriendAdapter;
import com.ntt.mediatn.FollowActivity;
import com.ntt.mediatn.Model.FriendsModel;
import com.ntt.mediatn.Model.User;
import com.ntt.mediatn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Fragment_Profile extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FriendsModel> list;
    ImageView changeCvImg, coverPhoto, verifiedAcc, profileImage;
    TextView followrv, follower, userName, job;

    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;

    public Fragment_Profile(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ActivityResultLauncher<Intent> activityResultLauncherProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__profile, container, false);

        recyclerView = view.findViewById(R.id.friendRv);
        changeCvImg = view.findViewById(R.id.changeCoverPhoto);
        coverPhoto = view.findViewById(R.id.coverPhoto);
        userName = view.findViewById(R.id.userName);
        job = view.findViewById(R.id.job);
        verifiedAcc = view.findViewById(R.id.verifiedAccount);
        profileImage = view.findViewById(R.id.profile_image);
        followrv = view.findViewById(R.id.followRv);
        follower = view.findViewById(R.id.follower);

        // lấy data của user
        database.getReference().child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    Picasso.get()
                            .load(user.getCoverPhoto())
                            .placeholder(R.drawable.placeholder)
                            .into(coverPhoto);
                    Picasso.get()
                            .load(user.getProfile())
                            .placeholder(R.drawable.placeholder)
                            .into(profileImage);
                    userName.setText(user.getName());
                    job.setText(user.getJob());
                    follower.setText(user.getFollowerCount()+" ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        list = new ArrayList<>();
        list.add(new FriendsModel(R.drawable.ava, "Thành"));
        list.add(new FriendsModel(R.drawable.asus, "Đại"));
        list.add(new FriendsModel(R.drawable.ava, "Thành"));
        list.add(new FriendsModel(R.drawable.story, "Bảo"));
        list.add(new FriendsModel(R.drawable.ava, "Thành"));

        FriendAdapter adapter = new FriendAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        //xác minh tài khoản
        verifiedAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncherProfile.launch(intent);
            }
        });

        // Khởi tạo ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        coverPhoto.setImageURI(uri);

                        final StorageReference reference = storage.getReference().child("Anh_bia")
                                .child(FirebaseAuth.getInstance().getUid());
                        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(requireContext(), "ảnh bìa đã lưu", Toast.LENGTH_SHORT).show();  // getcontext > requireContext
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        database.getReference().child("Users").child(auth.getUid()).child("coverPhoto").setValue(uri.toString());
                                    }
                                });
                            }
                        });
                    }
                }
        );
        activityResultLauncherProfile = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        profileImage.setImageURI(uri);

                        final StorageReference reference = storage.getReference().child("anh_profile")
                                .child(FirebaseAuth.getInstance().getUid());
                        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(requireContext(), "ảnh đại diện đã lưu", Toast.LENGTH_SHORT).show(); // getcontext > requireContext
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        database.getReference().child("Users").child(auth.getUid()).child("profile").setValue(uri.toString());
                                    }
                                });
                            }
                        });
                    }
                }
        );

        changeCvImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        followrv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), FollowActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
