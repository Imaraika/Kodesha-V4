package com.example.kodesha;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;
    @BindView(R.id.buttonfindhouse) Button mainFindHouseButton;
    @BindView(R.id.locationEditText)EditText mainLocationEditText;
    @BindView(R.id.imageView1) ImageView mainContainerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // butterknife for our BindViews
        ButterKnife.bind(this);

        mainFindHouseButton.setOnClickListener(this);
    }

            @Override
            public void onClick(View v) {

                // getting text from EditText

                String chosenLotion = mainLocationEditText.getText().toString();

                saveLocationToFirebase(chosenLotion);


                //intent to open onother activity and get information from first activity

                Intent houseIntent = new Intent(MainActivity.this,Houses.class);
                houseIntent.putExtra("location",chosenLotion);
                startActivity(houseIntent);


            }


    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);

    }

    }
