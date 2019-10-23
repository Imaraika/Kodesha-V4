package com.example.kodesha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kodesha.adapters.HouseListAdapter;
import com.example.kodesha.models.Business;
import com.example.kodesha.models.Category;
import com.example.kodesha.models.YelpBusinessRenting;
import com.example.kodesha.network.YelpApi;
import com.example.kodesha.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Houses extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private HouseListAdapter mAdapter;

    @BindView(R.id.errorTextView) TextView mErrorTextView;

    public List<Business> houses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");


                 //integrate API

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessRenting> call = client.getHouses(location, "houses");
        call.enqueue(new Callback<YelpBusinessRenting>() {
            @Override
            public void onResponse(Call<YelpBusinessRenting> call, Response<YelpBusinessRenting> response) {

                if (response.isSuccessful()) {
                    houses = response.body().getBusinesses();
                    mAdapter = new HouseListAdapter(Houses.this, houses);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(Houses.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showHouses();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessRenting> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);

                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showHouses() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
