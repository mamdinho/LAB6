package com.example.lab6;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Donations.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DonationDao DonationDao();  //returns the DAO interface
}
