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
        String[] hsesRoad = new String[]{"707 Kicukiro Ave", "2206 gisz ruhango GD",
                "2816 Beletoire Ave", "8227 Folcroft kigali ", "9227 lene KK", "2227 nyarugenge Ave",
                "8227 sake avenue", "8227 Folcroft Lane", "8227 nyabugoogo avenue", "8227 kigali avenue",
                "1227 sahara avenue", "8227 nyugwe avenue", "8227 ruyenzi avenue",
                "2422 kamonyi avenue", "8220 nyamata avenue"};

        dispLocationText = (TextView) findViewById(R.id.display_Location_TextView);
        listOfHouses = (ListView) findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,hsesRoad);
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
