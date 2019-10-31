package com.example.kodesha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.kodesha.R;
import com.example.kodesha.SavedHouseListActivity;
import com.example.kodesha.util.ItemTouchHelperAdapter;
import com.example.kodesha.models.Business;
import com.example.kodesha.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseHouseListAdapter extends FirebaseRecyclerAdapter<Business, FirebaseHouseViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseHouseListAdapter(FirebaseRecyclerOptions<Business> options, Query query, SavedHouseListActivity savedHouseListActivity, SavedHouseListActivity houseListActivity){
        super(options);
//        mRef = ref.getRef();
//        mOnStartDragListener = onStartDragListener;
//        mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseHouseViewHolder firebaseHouseViewHolder, int position, @NonNull Business restaurant) {
        firebaseHouseViewHolder.bindHouse(restaurant);
        firebaseHouseViewHolder.mRestaurantImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(firebaseHouseViewHolder);
                }
                return false;
            }
        });
    }

    @NonNull
    @Override
    public FirebaseHouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_list_item_drag, parent, false);
        return new FirebaseHouseViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        getRef(position).removeValue();
    }
}