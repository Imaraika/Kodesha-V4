package com.example.kodesha;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kodesha.models.Business;
import com.example.kodesha.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HouseDetailFragment extends Fragment  implements View.OnClickListener {
    @BindView(R.id.houseImageView)
    ImageView mImageLabel;
    @BindView(R.id.houseNameTextView)
    TextView mNameLabel;
    @BindView(R.id.categTextView)
    TextView mCategoriesLabel;

    @BindView(R.id.websiteTextView)
    TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView)
    TextView mPhoneLabel;
    @BindView(R.id.addressTextView)
    TextView mAddressLabel;
    @BindView(R.id.savehouseButton)
    TextView mSavehouseButton;

    private Business mHouse;

    public HouseDetailFragment() {
        // Required empty public constructor
    }

    public static HouseDetailFragment newInstance(Business house) {
        HouseDetailFragment houseDetailFragment = new HouseDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("house", Parcels.wrap(house));
       houseDetailFragment.setArguments(args);
        return houseDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHouse = Parcels.unwrap(getArguments().getParcelable("house"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_detail, container, false);
        ButterKnife.bind(this, view);


        Picasso.get().load(mHouse.getImageUrl()).into(mImageLabel);
        List<String> categories = new ArrayList<>();

        for (Category category : mHouse.getCategories()) {
            categories.add(category.getTitle());

            mWebsiteLabel.setOnClickListener(this);
            mPhoneLabel.setOnClickListener(this);
            mAddressLabel.setOnClickListener(this);
            return view;
        }

        mNameLabel.setText(mHouse.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        mPhoneLabel.setText(mHouse.getPhone());
        mAddressLabel.setText(mHouse.getLocation().toString());

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mHouse.getUrl()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mHouse.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mHouse.getCoordinates().getLatitude()
                            + "," + mHouse.getCoordinates().getLongitude()
                            + "?q=(" + mHouse.getName() + ")"));
            startActivity(mapIntent);
        }

    }
}