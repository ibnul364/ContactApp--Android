package com.example.contactapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    private EditText editName,editSurname;
    private Button addButton,showButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        //EditText
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editPhone);
        //Button
        addButton = findViewById(R.id.savebuttonId);
        showButton = findViewById(R.id.showbuttonId);



        //Adding the Value
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString();
                String surName = editSurname.getText().toString();

                if(view.getId()==R.id.savebuttonId){

//                    if(name.length() !=0 && surName.length() !=0)
                      if(name.equals("") || surName.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"You must put something in the text field",Toast.LENGTH_LONG).show();
                    }else{

                          long rowNum= databaseHelper.saveData(name,surName);
                          if(rowNum > -1){
                              Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_LONG).show();
                              editName.setText("");
                              editSurname.setText("");
                          }else{
                              Toast.makeText(getApplicationContext(),"Data is not inserted",Toast.LENGTH_LONG).show();
                          }

                      }

                }

            }
        });

        //Showing the Value
        showButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view.getId()==R.id.showbuttonId){
                    Intent  intent = new Intent(MainActivity.this,ListDataActivity.class);
                    startActivity(intent);
                }



            }
        });




    }
}