package com.example.lab6;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DonationAdapter extends BaseAdapter {

    private final Activity context;
    ArrayList<Donations> donationsArrayList;
    LayoutInflater inflater;
    View view;
    public DonationAdapter(Activity appContext, ArrayList<Donations> list){
        //super(appContext, R.layout.donation_listitem_layout ,donationsArrayList);
        this.context = appContext;
        this.donationsArrayList = list;
        inflater = appContext.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return donationsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return donationsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = inflater.inflate(R.layout.donation_listitem_layout,null, true);
        TextView amountText = (TextView)view.findViewById(R.id.amount_row_id);
        TextView paymentText = (TextView) view.findViewById(R.id.payment_row_id);
        TextView sharingText = (TextView) view.findViewById(R.id.sharing_row_id);

        amountText.setText(String.valueOf(donationsArrayList.get(position).amount));
        if (donationsArrayList.get(position).paymentMethod == 1 )
            paymentText.setText("Credit Card");
        else
            paymentText.setText("PayPal");


        /*String sharing = "";
        if (donationsArrayList.get(position).sharingApps[0] == 1){
            sharing += " What's app";
        }
        if (donationsArrayList.get(position).sharingApps[1] == 1){
            sharing += " text messages";

        }
        if (donationsArrayList.get(position).sharingApps[2] == 1){
            sharing += " messenger";

        }
        sharingText.setText(sharing);*/


        return view;
    }


}
