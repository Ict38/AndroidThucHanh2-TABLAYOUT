package com.example.thuchanh2_bai4.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.thuchanh2_bai4.fragment.FragmentInfo;
import com.example.thuchanh2_bai4.fragment.FragmentList;
import com.example.thuchanh2_bai4.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentStatePagerAdapter {


    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentList();
            case 1: return new FragmentInfo();
            case 2: return new FragmentSearch();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
