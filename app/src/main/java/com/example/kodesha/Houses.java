package com.example.kodesha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Houses extends AppCompatActivity {

    @BindView(R.id.listView) ListView listOfHouses;
    @BindView(R.id.display_Location_TextView) TextView dispLocationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        ButterKnife.bind(this);

        int[] Images = {R.drawable.backgr1, R.drawable.backgr2, R.drawable.kumaziphoto, R.drawable.mostbeautfl, R.drawable.onather1};
        String[] hsesRoad = new String[]{"Mi Mero Mole", "Mother's Bistro",
                "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
                "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
                "Lardo", "Portland City Grill", "Fat Head's Brewery",
                "Chipotle", "Subway"};

        dispLocationText = (TextView) findViewById(R.id.display_Location_TextView);
        listOfHouses = (ListView) findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,hses);
        listOfHouses.setAdapter(arrayAdapter);
        listOfHouses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String houses = ((TextView) view).getText().toString();
                Toast.makeText(Houses.this, houses, Toast.LENGTH_SHORT).show();
            }

        });
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        dispLocationText.setText("Houses available at "+ location);
    }
}
