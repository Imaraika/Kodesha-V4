package com.example.kodesha.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kodesha.Constants;
import com.example.kodesha.HouseDetailActivity;
import com.example.kodesha.R;
import com.example.kodesha.models.Business;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseHouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;
    public ImageView mRestaurantImageView;

    public FirebaseHouseViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindHouse(Business restaurant){
        mRestaurantImageView = mView.findViewById(R.id.restaurantImageView);
        TextView nameTextView = mView.findViewById(R.id.restaurantNameTextView);
        TextView categoryTextView = mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = mView.findViewById(R.id.ratingTextView);

        nameTextView.setText(restaurant.getName());
//        categoryTextView.setText(restaurant.getCategories().get(0));
        ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        Picasso.get().load(restaurant.getImageUrl()).into(mRestaurantImageView);
    }

    @Override
    public void onClick(View view){
        final ArrayList<Business> restaurants = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOUSES).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    restaurants.add(snapshot.getValue(Business.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, HouseDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(restaurants));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}