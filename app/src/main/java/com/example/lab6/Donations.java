package com.example.lab6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "donations")
public class Donations implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    int donationId;

    int paymentMethod; // 1 for credit and 2 for paypal
    double amount;

    @Ignore
    int[] sharingApps;


    public Donations(int donationid, int paymentMethod, double amount, int[] sharingApps) {
        this.donationId = donationid;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.sharingApps = sharingApps;
    }

    public Donations(){}
    protected Donations(Parcel in) {
        donationId = in.readInt();
        paymentMethod = in.readInt();
        amount = in.readDouble();
        sharingApps = in.createIntArray();
    }

    public static final Creator<Donations> CREATOR = new Creator<Donations>() {
        @Override
        public Donations createFromParcel(Parcel in) {
            return new Donations(in);
        }

        @Override
        public Donations[] newArray(int size) {
            return new Donations[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(donationId);
        dest.writeInt(paymentMethod);
        dest.writeDouble(amount);
        dest.writeIntArray(sharingApps);
    }
}
