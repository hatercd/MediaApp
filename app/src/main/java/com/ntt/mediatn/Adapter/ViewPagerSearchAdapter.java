package com.ntt.mediatn.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ntt.mediatn.Fragment.Fragment_Activity2;
import com.ntt.mediatn.Fragment.Fragment_Request;
import com.ntt.mediatn.Fragment.Fragment_SearchPost;
import com.ntt.mediatn.Fragment.Fragment_SearchUser;

public class ViewPagerSearchAdapter extends FragmentStateAdapter {

    public ViewPagerSearchAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new Fragment_SearchUser();
            case 1: return new Fragment_SearchPost();
            default: return new Fragment_SearchUser();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "Người dùng";
        }else if (position == 1){
            title = "Bài viết";
        }

        return title;
    }
}
