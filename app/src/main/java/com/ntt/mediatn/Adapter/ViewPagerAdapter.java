package com.ntt.mediatn.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ntt.mediatn.Fragment.Fragment_Activity2;
import com.ntt.mediatn.Fragment.Fragment_Request;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new Fragment_Activity2();
            case 1: return new Fragment_Request();
            default: return new Fragment_Activity2();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = " Thông báo";
        }else if (position == 1){
            title = "Phản hồi";
        }

        return title;
    }
}
