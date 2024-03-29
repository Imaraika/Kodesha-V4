package com.example.kodesha.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kodesha.R;

import java.util.ArrayList;

public class ListingAdapter extends BaseAdapter {
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