package com.example.myapplication.dp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class HelperClass extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "student.dp";
    private static String TABLE_NAME = "profile";
    private static String UID = "id";
    private static String USERNAME = "username";
    private static String AGE = "age";
    Context context;


    public static class DatabaseAdapter {
        Context context;
        HelperClass helperClass;

        public DatabaseAdapter(Context context) {
            this.context = context;
            helperClass = new HelperClass(this.context, DATABASE_NAME, null, 1);
        }

        public void Add(String user, String age) {
            try {
                SQLiteDatabase database = helperClass.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(USERNAME, user);
                values.put(AGE, age);
                Long inserted = database.insert(TABLE_NAME, null, values);
                this.Print("Add" + " " + inserted);
            } catch (Exception e) {
                this.Print(e.getMessage());
            }

        }

        public void Delete() {
            try {
                SQLiteDatabase database = helperClass.getWritableDatabase();


                int deleted = database.delete(TABLE_NAME, null, null);
                this.Print("Deleted" + "Row  " + deleted);
            } catch (Exception e) {
                this.Print(e.getMessage());
            }

        }


        public String GetUser() {

            SQLiteDatabase database = helperClass.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            String Reuslt = "";


            while (cursor.moveToNext()) {
                Reuslt = Reuslt + cursor.getString(1) + " " + cursor.getString(2) + "\n";

            }
            return Reuslt;

        }

        private void Print(String add) {
            Toast.makeText(context, add, Toast.LENGTH_LONG).show();
        }

        public void CloseDateBase() {
            helperClass.close();
            this.Print("Closed");

        }
    }


    public HelperClass(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String QUERY = "CREATE TABLE " + TABLE_NAME +
                    "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERNAME + " TEXT," + AGE + " TEXT" + ");";


            db.execSQL(QUERY);

        } catch (Exception e) {
            this.Print(e.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);

    }

    public void Print(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
