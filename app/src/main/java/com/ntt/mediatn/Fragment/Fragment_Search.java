package com.ntt.mediatn.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ntt.mediatn.Adapter.ViewPagerAdapter;
import com.ntt.mediatn.Adapter.ViewPagerSearchAdapter;
import com.ntt.mediatn.R;

public class Fragment_Search extends Fragment {


    ViewPager2 viewPagerSearch;
    TabLayout tabLayoutSearch;

    public Fragment_Search() {
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
        View view = inflater.inflate(R.layout.fragment__search, container, false);

        viewPagerSearch = view.findViewById(R.id.viewPagerSearch);
        ViewPagerSearchAdapter adapter = new ViewPagerSearchAdapter(requireActivity());
        viewPagerSearch.setAdapter(adapter);

        tabLayoutSearch = view.findViewById(R.id.tabLayoutSearch);
        new TabLayoutMediator(tabLayoutSearch, viewPagerSearch, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Người dùng"); break;
                case 1: tab.setText("Bài viết"); break;
            }
        }).attach();

        return view;
    }
}