package com.example.myapplication.roomdp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Query("DELETE FROM Estudent")
    public void deleteAll();

    @Insert
    public void insert(Estudent estudent);

    @Query("SELECT * FROM Estudent WHERE uid = :s")
    public Estudent getStudent(String s);

    @Query("SELECT * FROM Estudent")
    public List<Estudent> getAllStudent();

}
