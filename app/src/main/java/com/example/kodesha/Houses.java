package com.example.kodesha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Houses extends AppCompatActivity {
    int[] IMAGES = {R.drawable.backgr1, R.drawable.backgr1, R.drawable.backgr1, R.drawable.backgr1,
            R.drawable.mostbeautfl,R.drawable.kumaziphoto, R.drawable.kodeshapngpic, R.drawable.backgr1, R.drawable.backgr1, R.drawable.backgr1};

    String[] hsesRoad = {"707 Kicukiro Ave", "2206 gisz ruhango GD","2816 Beletoire Ave", "8227 Folcroft kigali", "9227 lene KK","707 Kicukiro Ave",
            "2206 gisz ruhango GD","2816 Beletoire Ave", "8227 Folcroft kigali", "9227 lene KK"};



    @BindView(R.id.listView) ListView listOfHouses;
    @BindView(R.id.display_Location_TextView) TextView dispLocationText;
//    @BindView(R.id.imageViewoflist) ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        ButterKnife.bind(this);


//        img = (ImageView) findViewById(R.id.imageViewoflist) ;
        dispLocationText = (TextView) findViewById(R.id.display_Location_TextView);
        listOfHouses = (ListView) findViewById(R.id.listView);


        CustomerAdapter customerAdapter = new CustomerAdapter();
        listOfHouses.setAdapter(customerAdapter);

//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hsesRoad);
//        listOfHouses.setAdapter(arrayAdapter);

        listOfHouses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String houses = ((TextView) view).getText().toString();
                Toast.makeText(Houses.this, houses, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ListdataActivity.class);
                intent.putExtra("image",IMAGES[position]);
                intent.putExtra("name",hsesRoad[position]);
                startActivity(intent);

            }

        });
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        dispLocationText.setText("Houses available at " + location);

    }

     class CustomerAdapter extends BaseAdapter{

         @Override
         public int getCount() {
          return IMAGES.length;
         }

         @Override
         public Object getItem(int position) {
             return null;
         }

         @Override
         public long getItemId(int position) {
             return 0;
         }

         @Override
         public View getView(int i, View convertView, ViewGroup parent) {
             convertView = getLayoutInflater().inflate(R.layout.customlayout,null);

             ImageView img = (ImageView)convertView.findViewById(R.id.imageViewoflist);
             TextView textView_Road = (TextView)convertView.findViewById(R.id.textView_Road);
             TextView textView_descr = (TextView)convertView.findViewById(R.id.textView_descrp);
             img.setImageResource(IMAGES[i]);

//             iimm.setImageResource(IMAGES[i]);
             textView_Road.setText(hsesRoad[i]);
//             textView_descr.setText(hsesRoad[i]);

             return convertView;
         }
     }
}
