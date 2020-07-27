package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.roomdp.DAO;

import com.example.myapplication.roomdp.Estudent;
import com.example.myapplication.roomdp.roomdatabase;


import com.example.myapplication.dp.HelperClass.*;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    Button buttonView, buttonAdd, buttonDelete, buttonAddRoom, buttonViewRoom, buttonDeleteRoom;
    EditText textAGE, textUser;
    TextView view;

    DatabaseAdapter adapter;
    roomdatabase roomdatabase;
    DAO dao;

    ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intiaLize RoomDatabase
        service = Executors.newFixedThreadPool(3);
//Threadbool
        roomdatabase = Room.databaseBuilder(getApplicationContext(), roomdatabase.class, "students").build();
        dao = roomdatabase.Getdao();

        buttonAdd = (Button) findViewById(R.id.button2);
        buttonView = (Button) findViewById(R.id.button);
        buttonDelete = (Button) findViewById(R.id.buttonDelet);
        textAGE = (EditText) findViewById(R.id.Age);
        textUser = (EditText) findViewById(R.id.UseName);
        view = (TextView) findViewById(R.id.textView);

        buttonAddRoom = (Button) findViewById(R.id.buttonAddRoom);
        buttonDeleteRoom = (Button) findViewById(R.id.buttonRoomDelet);
        buttonViewRoom = (Button) findViewById(R.id.buttonViewRoom);

        buttonViewRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        List<Estudent> allStudent = dao.getAllStudent();
                        Iterator<Estudent> iterator = allStudent.iterator();
                        String s = "";
                        while (iterator.hasNext()) {
                            Estudent l = iterator.next();
                            s = l.getName() + " " + l.getAge() + "\n";
                        }
                        view.setText(s);
                    }
                });

            }
        });
        buttonDeleteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        dao.deleteAll();

                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
                    }

                });


            }
        });
        buttonAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.submit(new Runnable() {
                    @Override

                    public void run() {

                        dao.insert(new Estudent(textUser.getText().toString(), textAGE.getText().toString()));
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });


        adapter = new DatabaseAdapter(getApplicationContext());
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.Add(textUser.getText().toString(), textAGE.getText().toString());
                //Toast.makeText(getApplicationContext(), textUser.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText(adapter.GetUser());

            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.Delete();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.CloseDateBase();

    }
}