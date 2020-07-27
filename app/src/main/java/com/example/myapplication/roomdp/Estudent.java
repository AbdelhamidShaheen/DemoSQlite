package com.example.myapplication.roomdp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Estudent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    public int uid;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public String age;

    public Estudent(String name, String age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }


    public String getAge() {
        return age;
    }

    public int getUid() {
        return uid;
    }
}
