package com.example.kodesha.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kodesha.Constants;
import com.example.kodesha.HouseDetailActivity;
import com.example.kodesha.R;
import com.example.kodesha.models.Business;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseHouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseHouseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindHouse(Business house) {
        ImageView houseImageView = (ImageView) mView.findViewById(R.id.houseImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.houseNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView phoneTextView = (TextView) mView.findViewById(R.id.phoneTextView);

        Picasso.get().load(house.getImageUrl()).into(houseImageView);

        nameTextView.setText(house.getName());
//        categoryTextView.setText(house.getCategories().get(0));
        phoneTextView.setText(house.getPhone());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Business> houses = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOUSES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    houses.add(snapshot.getValue(Business.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, HouseDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("houses", Parcels.wrap(houses));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}