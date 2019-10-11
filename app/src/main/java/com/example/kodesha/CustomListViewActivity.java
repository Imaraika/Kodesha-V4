package com.example.kodesha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CustomListViewActivity extends AppCompatActivity {

    int[] IMAGES = {R.drawable.backgr1,R.drawable.backgr1,R.drawable.backgr1,R.drawable.backgr1,R.drawable.backgr1};
    String[] houseRoad = {"707 Kicukiro Ave","2206 gisz ruhango GD","2816 Beletoire Ave","8227 Folcroft kigali ","9227 lene KK"};
//    String[] descrp = {"2227 nyarugenge Ave","8227 sake avenue","8227 Folcroft Lane","8227 nyabugoogo avenue","8227 kigali avenue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        ListView lst =(ListView)findViewById(R.id.listv1);

        CustmAdapter customerAdapter = new CustmAdapter();
        lst.setAdapter(customerAdapter);
    }
    class CustmAdapter extends BaseAdapter{
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
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageViewoflist = (ImageView)convertView.findViewById(R.id.imageViewoflist);
            TextView textView_Road = (TextView)convertView.findViewById(R.id.textView_Road);
            TextView textView_descr = (TextView)convertView.findViewById(R.id.textView_descrp);

//
            imageViewoflist.setImageResource(IMAGES[position]);
            textView_Road.setText(houseRoad[position]);

            return convertView;

        }
    }

}
