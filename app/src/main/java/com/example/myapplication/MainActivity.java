package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.dp.HelperClass.*;

public class MainActivity extends AppCompatActivity {
    Button buttonView, buttonAdd, buttonDelete;
    EditText textAGE, textUser;
    TextView view;
    DatabaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.button2);
        buttonView = (Button) findViewById(R.id.button);
        buttonDelete = (Button) findViewById(R.id.buttonDelet);
        textAGE = (EditText) findViewById(R.id.Age);
        textUser = (EditText) findViewById(R.id.UseName);
        view = (TextView) findViewById(R.id.textView);

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