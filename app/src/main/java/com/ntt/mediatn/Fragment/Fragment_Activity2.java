package com.ntt.mediatn.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntt.mediatn.Adapter.ActivityAdapter;
import com.ntt.mediatn.Model.ActivityModel;
import com.ntt.mediatn.R;

import java.util.ArrayList;


public class Fragment_Activity2 extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ActivityModel> list;

    public Fragment_Activity2() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__activity2, container, false);

        recyclerView = view.findViewById(R.id.activity2Rv);

        list = new ArrayList<>();
        list.add(new ActivityModel(R.drawable.ava,"<b>Trần Văn</b> nhắc bạn trong một bình luận : thử lại","1p trước"));
        list.add(new ActivityModel(R.drawable.asus,"<b>Trần Văn</b> thích bài viết của bạn.","10p trước"));
        list.add(new ActivityModel(R.drawable.asus,"<b>Trần Văn</b> thích bài viết của bạn.","11p trước"));
        list.add(new ActivityModel(R.drawable.ava,"<b>Trần Văn</b> nhắc bạn trong một bình luận : thử lại","12p trước"));
        list.add(new ActivityModel(R.drawable.story,"<b>Trần Văn</b> thích ảnh của bạn.","25p trước"));
        list.add(new ActivityModel(R.drawable.ava,"<b>Trần Văn</b> nhắc bạn trong một bình luận.","30p trước"));
        list.add(new ActivityModel(R.drawable.ava,"<b>Trần Văn</b> đã trả lời bình luận của bạn: Cái gì thế?","31p trước"));
        list.add(new ActivityModel(R.drawable.story,"<b>Trần Văn</b> thích bài viết của bạn.","33p trước"));
        list.add(new ActivityModel(R.drawable.ava,"<b>Trần Văn</b> nhắc bạn trong một bình luận.","50p trước"));
        list.add(new ActivityModel(R.drawable.ava,"<b>Trần Văn</b> nhắc bạn trong một bình luận.","1h trước"));

        ActivityAdapter adapter = new ActivityAdapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}