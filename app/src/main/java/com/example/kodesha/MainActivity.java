package com.example.kodesha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.buttonfindhouse) Button mainFindHouseButton;
    @BindView(R.id.locationEditText)EditText mainLocationEditText;
//    @BindView(R.id.imageView) ImageView mainLogoImageView;
    @BindView(R.id.imageView1) ImageView mainContainerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainFindHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String chosenLotion = mainLocationEditText.getText().toString();

                Intent houseIntent = new Intent(MainActivity.this,CustomListViewActivity.class);
//                houseIntent.putExtra("location",chosenLotion);
                startActivity(houseIntent);


            }
        });

    }
}
