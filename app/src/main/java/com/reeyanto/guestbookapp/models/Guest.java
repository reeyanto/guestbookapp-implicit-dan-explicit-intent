package com.reeyanto.guestbookapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Guest implements Parcelable {

    private String nama, email, telp;

    public Guest(String nama, String email, String telp) {
        this.nama = nama;
        this.email = email;
        this.telp = telp;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getTelp() {
        return telp;
    }

    protected Guest(Parcel in) {
        nama = in.readString();
        email = in.readString();
        telp = in.readString();
    }

    public static final Creator<Guest> CREATOR = new Creator<Guest>() {
        @Override
        public Guest createFromParcel(Parcel in) {
            return new Guest(in);
        }

        @Override
        public Guest[] newArray(int size) {
            return new Guest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(email);
        parcel.writeString(telp);
    }
}
