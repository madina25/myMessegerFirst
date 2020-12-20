package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }

    public void showDialog(View v) {
        ModalDialog dialog = new ModalDialog();
        dialog.show(getSupportFragmentManager(), "modalDialog");
    }

    public void getFromDB(){
        SQLiteDatabase myFirstDB = getBaseContext().openOrCreateDatabase("notes.db", MODE_PRIVATE, null);
        myFirstDB.execSQL("create table students (id INT, note TEXT)");
        myFirstDB.execSQL("insert into students values(1, 'Test');");
    }
}