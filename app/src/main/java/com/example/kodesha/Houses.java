package com.example.kodesha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.listView)
    ListView listOfHouses;
    @BindView(R.id.display_Location_TextView)
    TextView dispLocationText;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        listOfHouses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String houses = ((TextView) view).getText().toString();
                Toast.makeText(Houses.this, houses, Toast.LENGTH_LONG).show();
            }
        });
        dispLocationText.setText("Here are all the restaurants near: " + location);

                 //integrate API

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessRenting> call = client.getHouses(location, "houses");
        call.enqueue(new Callback<YelpBusinessRenting>() {
            @Override
            public void onResponse(Call<YelpBusinessRenting> call, Response<YelpBusinessRenting> response) {

                if (response.isSuccessful()) {
                    List<Business> HousesList = response.body().getBusinesses();
                    String[] houses = new String[HousesList.size()];
                    String[] categories = new String[HousesList.size()];

                    for (int i = 0; i < houses.length; i++) {
                        houses[i] = HousesList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = HousesList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    ArrayAdapter adapter
                            = new MyHousesArrayAdapter(Houses.this, android.R.layout.simple_list_item_1, houses, categories);
                    listOfHouses.setAdapter(adapter);


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
        listOfHouses.setVisibility(View.VISIBLE);
        dispLocationText.setVisibility(View.VISIBLE);
    }
}
