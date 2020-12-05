package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllDonations extends AppCompatActivity {

    ListView myList;
    Activity activity;
    DonationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donations);

       /* donationList =  getIntent().getParcelableArrayListExtra("donationList");
        myList = (ListView) findViewById(R.id.alldonationlist);
        DonationAdapter adapter = new DonationAdapter(getApplicationContext(),donationList);
        myList.setAdapter(adapter);*/
        myList = (ListView) findViewById(R.id.alldonationlist);
        activity = this;

        DatabaseClient.databaseWriteExecutor.execute(()->{ //Below runnable will fetch all cars from the db and pass to adapter then set list adapter
            List<Donations> donationsFromDB =  DatabaseClient.getDbClient().DonationDao().getAll();
            ArrayList<Donations> donationList = new ArrayList<Donations>(0);
            donationList.addAll(donationsFromDB);
            adapter = new DonationAdapter(activity,donationList);
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    myList.setAdapter(adapter);
                }
            });
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filters the database
                if(newText.length() > 0){
                    try{
                        DatabaseClient.databaseWriteExecutor.execute(()->{ //Below runnable will fetch all cars from the db and pass to adapter then set list adapter
                            List<Donations> donationsFromDB =  DatabaseClient.getDbClient().DonationDao().getAllDonationWithAmountBiggerThan(Double.parseDouble(newText));
                            ArrayList<Donations> donationList = new ArrayList<Donations>(0);
                            donationList.addAll(donationsFromDB);
                            adapter = new DonationAdapter(activity,donationList);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    myList.setAdapter(adapter);
                                }
                            });
                        });
                    }catch(Exception e){
                        Toast.makeText(activity, "PLEASE ENTER ONLY NUMBERS", Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });
        return true;
    }
}
