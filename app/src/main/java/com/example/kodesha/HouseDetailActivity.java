package com.example.kodesha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.kodesha.adapters.HousePagerAdapter;
import com.example.kodesha.models.Business;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private HousePagerAdapter adapterViewPager;
    List<Business> mHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        ButterKnife.bind(this);

        mHouse = Parcels.unwrap(getIntent().getParcelableExtra("houses"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new HousePagerAdapter(getSupportFragmentManager(),mHouse);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
