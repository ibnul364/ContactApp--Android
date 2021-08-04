package com.example.contactapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    static DatabaseHelper databaseHelper;
    private ListView listView;
    private SQLiteDatabase sqLiteDatabase;
//    private Button backButton;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_data);

            databaseHelper = new DatabaseHelper(this);
            sqLiteDatabase = databaseHelper.getWritableDatabase();

            listView = findViewById(R.id.listViewId);

            loadData();

        }


            public  void loadData() {
                //Create an arraylist so that i can load the data to add in the  arraylist
                ArrayList<String> listData = new ArrayList<>();

                Cursor cursor = databaseHelper.showAllData();
//jodi kono cursor e row na thake
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "no data is available", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                listData.add(cursor.getString(1) + " \t " + cursor.getString(2));
            }
        }
                    //Array Adapter er maddome oi data ta listview e set korbo
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textViewId, listData);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedValue = adapterView.getItemAtPosition(position).toString();

                    cursor.moveToPosition(position);
                    String name = cursor.getString(1);
                    String surName = cursor.getString(2);
//                    listData.add(cursor.getString(1) + " \t " + cursor.getString(2));
                    Intent intent = new Intent(ListDataActivity.this, UpdateActivity.class);
                    Toast.makeText(getApplicationContext(), "Selected Value : " + selectedValue, Toast.LENGTH_LONG).show();
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    intent.putExtra("surName", surName);
                    startActivity(intent);
                    finish();

                }

        });


        }
}