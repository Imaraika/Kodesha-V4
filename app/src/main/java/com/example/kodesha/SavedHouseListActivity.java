package com.example.kodesha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kodesha.adapters.FirebaseHouseListAdapter;
import com.example.kodesha.adapters.FirebaseHouseViewHolder;
import com.example.kodesha.models.Business;
import com.example.kodesha.util.OnStartDragListener;
import com.example.kodesha.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedHouseListActivity extends AppCompatActivity {

    private DatabaseReference mRestaurantReference;
    private FirebaseHouseListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

        private void setUpFirebaseAdapter() {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            mRestaurantReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOUSES).child(uid);
            FirebaseRecyclerOptions<Business> options =
                    new FirebaseRecyclerOptions.Builder<Business>()
                            .setQuery(mRestaurantReference, Business.class)
                            .build();

            mFirebaseAdapter = new FirebaseHouseListAdapter(options) {
                @Override
                protected void onBindViewHolder(@NonNull FirebaseHouseViewHolder FirebaseHouseViewHolder, int position, @NonNull Business house) {
                    FirebaseHouseViewHolder.bindHouse(house);
                }

                @NonNull
                @Override
                public FirebaseHouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.houses_list_item, null, false);
                    return new FirebaseHouseViewHolder(view);
                }
            };


            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mFirebaseAdapter);
        }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}