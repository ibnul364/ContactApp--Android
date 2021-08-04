package com.example.contactapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class UpdateActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private String selectedName;
    private String selectedID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Intent receiveIntent = getIntent();

        selectedID = receiveIntent.getStringExtra("name");

        selectedName = receiveIntent.getStringExtra("surName");



    }
}