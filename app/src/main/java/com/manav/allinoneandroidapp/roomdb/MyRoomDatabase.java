package com.manav.allinoneandroidapp.roomdb;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import com.manav.allinoneandroidapp.model.CustomerModel;
import com.manav.allinoneandroidapp.services.CustomerDao;

@Database(entities = {CustomerModel.class}, version = 2)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();
}
