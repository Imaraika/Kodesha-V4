package com.example.kodesha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kodesha.R;
import com.example.kodesha.models.Business;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.HouseViewHolder> {
    private List<Business> mHouses;
    private Context mContext;

    public HouseListAdapter(Context context, List<Business> restaurants) {
        mContext = context;
        mHouses = restaurants;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.houses_list_item, parent, false);
        HouseViewHolder viewHolder = new HouseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        holder.bindRestaurant(mHouses.get(position));


    }

    @Override
    public int getItemCount() {
        return mHouses.size();
    }

    public class HouseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.restaurantImageView)
        ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView)
        TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.price) TextView mRatingTextView;

        private Context mContext;

        public HouseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRestaurant(Business restaurant) {
            mNameTextView.setText(restaurant.getName());
            mCategoryTextView.setText(restaurant.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }
    }
}