package com.example.mydukaan.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.mydukaan.fragment.categories.CategoriesFragment;
import com.example.mydukaan.fragment.product.ProductListFragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    private Context context;
    int totalTabs;
    public ViewPagerAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProductListFragment();
            case 1:
                return new CategoriesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
