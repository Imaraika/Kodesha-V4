package com.example.kodesha.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kodesha.HouseDetailActivity;
import com.example.kodesha.R;
import com.example.kodesha.models.Business;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.HouseViewHolder>  {

    private List<Business> mHouses;
    private Context mContext;
    private String[] house;

    public HouseListAdapter(Context context, List<Business> houses) {
        mContext = context;
        mHouses = houses;
    }


    public class HouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.houseImageView) ImageView mHouseImageView;
        @BindView(R.id.houseNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.price) TextView mPrice;
        private Context mContext;

        public HouseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

        }

        public void bindHouse(Business house) {
            Picasso.get().load(house.getImageUrl()).into(mHouseImageView);

            mNameTextView.setText(house.getName());
            mCategoryTextView.setText(house.getCategories().get(0).getTitle());
            mPrice.setText(house.getPhone());
        }


        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, HouseDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("houses", Parcels.wrap(mHouses));
            mContext.startActivity(intent);
        }
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
        holder.bindHouse(mHouses.get(position));

    }

    @Override
    public int getItemCount() {
        return mHouses.size();
    }


}