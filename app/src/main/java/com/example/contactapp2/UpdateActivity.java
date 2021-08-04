package com.example.contactapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    static DatabaseHelper databaseHelper;
    private String selectedName;
    private String selectedID;

    private EditText updateName,updatePhone;
    private Button updateButton,deleteButton;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);




        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Intent receiveIntent = getIntent();

        selectedID = receiveIntent.getStringExtra("name");

        selectedName = receiveIntent.getStringExtra("surName");

        //Update and Delete

        updateName = findViewById(R.id.updateNameId);
        updatePhone = findViewById(R.id.updatePhoneId);
        //Button
        updateButton = findViewById(R.id.updatebuttonId);
        deleteButton = findViewById(R.id.deletebuttonId);


        //pass the key
        id = getIntent().getExtras().getLong("id");
        String name = getIntent().getExtras().getString("name");
        String surName = getIntent().getExtras().getString("surName");

        updateName.setText(name);
        updatePhone.setText(surName);


//        String name = getIntent().getStringExtra(selectedID);
//        String surname = getIntent().getStringExtra(selectedName);

        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = updateName.getText().toString();
                String surName = updatePhone.getText().toString();

                if(view.getId()==R.id.updatebuttonId){

                    ListDataActivity.databaseHelper.updateInformation(id,name,surName);
                    startActivity(new Intent(UpdateActivity.this,ListDataActivity.class));
                    finish();

                }

            }
        });
    }


}