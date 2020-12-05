package com.example.lab6;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DonationDao {
    @Insert
     public void insert(Donations donations); //function to insert a donation object into db

    @Query("SELECT * FROM donations")
    public List<Donations> getAll();    //returns arrayList of all existing donations

    @Query("SELECT * FROM donations WHERE amount > :amountEntered")
    public List<Donations> getAllDonationWithAmountBiggerThan(double amountEntered);
}
