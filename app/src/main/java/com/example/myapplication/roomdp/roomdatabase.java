package com.example.myapplication.roomdp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Estudent.class}, version = 1)
public abstract class roomdatabase extends RoomDatabase {

    public abstract DAO Getdao();
}
