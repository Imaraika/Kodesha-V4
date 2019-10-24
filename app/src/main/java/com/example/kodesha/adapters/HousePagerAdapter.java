package com.example.kodesha.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.kodesha.HouseDetailFragment;
import com.example.kodesha.models.Business;

import java.util.List;

public class HousePagerAdapter extends FragmentPagerAdapter {
    private List<Business> mHouses;

    public HousePagerAdapter(FragmentManager fm, List<Business> houses) {
        super(fm);
        mHouses = houses;
    }

    @Override
    public Fragment getItem(int position) {
        return HouseDetailFragment.newInstance(mHouses.get(position));
    }

    @Override
    public int getCount() {
        return mHouses.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mHouses.get(position).getName();
    }
}