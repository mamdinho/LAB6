package com.example.lab6;

import android.content.Context;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {
    private static AppDatabase database;

    public static AppDatabase buildDbClient(Context context){
        if(database == null){ //only create instance of the database if it doesn't exist
            database = Room.databaseBuilder(context, AppDatabase.class, "donations_db").build();
        }
        return database;
    }

    public static AppDatabase getDbClient(){
        return database;
    }

    public static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(4);// build excutor Service

    //inserts a new Donations object into the Database
    public static void insertNewDonation (Donations newDonation){
        databaseWriteExecutor.execute(()->{//Runnable Command
            getDbClient().DonationDao().insert(newDonation);
        });
    }

}
