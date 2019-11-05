package com.example.kodesha.ui;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kodesha.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowUsers extends AppCompatActivity {
        ListView allusers;
        ProgressDialog mProgressDialog;
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        ListingAdapter adapter;
        ArrayList<User> users=new ArrayList<>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.showuser_actiivity);
            allusers=(ListView)findViewById(R.id.allusers);
            adapter=new ListingAdapter(ShowUsers.this,users);
            allusers.setAdapter(adapter);
            getDataFromServer();
        }
        // getting the data from UserNode at Firebase and then adding the users in Arraylist and setting it to Listview
        public void getDataFromServer()
        {
            showProgressDialog();
            databaseReference.child("UserNode").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                        {
                            User user=postSnapShot.getValue(User.class);
                            users.add(user);
                            adapter.notifyDataSetChanged();
                        }
                    }
                    hideProgressDialog();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    hideProgressDialog();
                }
            });
        }
        private void showProgressDialog() {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(ShowUsers.this);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(true);
            }
            mProgressDialog.show();
        }
        private void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
        private class ListingAdapter extends BaseAdapter {
            Context context;
            LayoutInflater layoutInflater;
            ArrayList<User> users;
            public ListingAdapter(Context con,ArrayList<User> users)
            {
                context=con;
                layoutInflater = LayoutInflater.from(context);
                this.users=users;
            }
            @Override
            public int getCount() {
                return users.size();
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = layoutInflater.inflate(R.layout.adapter_listing, null, false);
                    holder = new ViewHolder();
                    holder.fullname = (TextView) convertView.findViewById(R.id.user_fullname);
                    holder.email = (TextView) convertView.findViewById(R.id.user_email);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                User user=users.get(position);
                holder.fullname.setText(user.getFirstname()+user.getLastname());
                holder.email.setText(user.getEmail());
                return convertView;
            }
            public class ViewHolder {
                TextView fullname, email;
            }
            @Override
            public Object getItem(int position) {
                return users.get(position);
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
        }

    }
