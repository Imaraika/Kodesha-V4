package com.example.kodesha;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kodesha.ui.LoginActivity;
import com.example.kodesha.ui.ShowUsers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;

    @BindView(R.id.savedHousesButton) Button mSavedHousesButton;
    @BindView(R.id.buttonfindhouse) Button mainFindHouseButton;
    @BindView(R.id.locationEditText)EditText mainLocationEditText;
    @BindView(R.id.imageView1) ImageView mainContainerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        mSearchedLocationReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // butterknife for our BindViews
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };

        mainFindHouseButton.setOnClickListener(this);

        mSavedHousesButton.setOnClickListener(this);



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

                if (v == mSavedHousesButton) {
                    Intent intent = new Intent(MainActivity.this, ShowUsers.class);
                    startActivity(intent);
                }
            }


    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
