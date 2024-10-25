package com.ntt.mediatn.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntt.mediatn.Adapter.StoryAdapter;
import com.ntt.mediatn.Adapter.DashboardAdapter;
import com.ntt.mediatn.Model.Post;
import com.ntt.mediatn.Model.StoryModel;
import com.ntt.mediatn.R;

import java.util.ArrayList;

public class Fragment_Home extends Fragment {

    RecyclerView storyRv, dashboardRv;
    ArrayList<StoryModel> list;
    ArrayList<Post> dashboardList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__home, container, false);

        storyRv = view.findViewById(R.id.storyRV);

        list = new ArrayList<>();
        list.add(new StoryModel(R.drawable.ava, R.drawable.icon_videocall,R.drawable.story, "Quốc"));
        list.add(new StoryModel(R.drawable.story, R.drawable.icon_live,R.drawable.story, "Quốc"));
        list.add(new StoryModel(R.drawable.asus, R.drawable.icon_videocall,R.drawable.ava, "Quốc"));
        list.add(new StoryModel(R.drawable.ava, R.drawable.icon_videocall,R.drawable.story, "Quốc"));

        StoryAdapter adapter = new StoryAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        storyRv.setLayoutManager(linearLayoutManager);
        storyRv.setNestedScrollingEnabled(false);
        storyRv.setAdapter(adapter);

        dashboardRv = view.findViewById(R.id.dashboardRv);
        dashboardList = new ArrayList<>();

//        dashboardList.add(new Post(R.drawable.ava, R.drawable.story, R.drawable.icon_redsave, "Manh Nguyen","Du lich", "299","12", "2"));
//        dashboardList.add(new Post(R.drawable.asus, R.drawable.asus, R.drawable.icon_redsave, "Hanh Pham","Du lich", "299","12", "2"));
//        dashboardList.add(new Post(R.drawable.asus, R.drawable.asus, R.drawable.icon_redsave, "Manh Nguyen","Du lich", "299","12", "2"));
//        dashboardList.add(new Post(R.drawable.ava, R.drawable.story, R.drawable.icon_redsave, "Manh Nguyen","Du lich", "299","12", "2"));
//        dashboardList.add(new Post(R.drawable.ava, R.drawable.story, R.drawable.icon_redsave, "Manh Nguyen","Du lich", "299","12", "2"));

        DashboardAdapter dashboardAdapter = new DashboardAdapter(dashboardList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        dashboardRv.setLayoutManager(layoutManager);
        dashboardRv.setNestedScrollingEnabled(false);
        dashboardRv.setAdapter(dashboardAdapter);

        return view;
    }
}