package com.example.kodesha;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyHousesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mHouses;
    private String[] mCuisines;

    public MyHousesArrayAdapter (Context mContext, int resource, String[] mHouses, String[] mCuisines){
        super(mContext, resource);
        this.mContext = mContext;
        this.mCuisines = mCuisines;
        this.mHouses= mHouses;
    }

    @Override
    public Object getItem(int position){
        String house = mHouses[position];
        String cuisine = mCuisines[position];
        return String.format("%s \n Serves great: %s",house, cuisine);
    }

    @Override
    public int getCount(){
        return mHouses.length;
    }
}