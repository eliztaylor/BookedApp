package com.example.bookedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    //Declare and define variables used
    EditText et_titleName, et_authorName, et_bookSynopsis;
    Button btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //Set ids of varaibles
        et_titleName = findViewById(R.id.et_titleName);
        et_authorName = findViewById(R.id.et_authorName);
        et_bookSynopsis = findViewById(R.id.et_bookSynopsis);

        btn_add = findViewById(R.id.btn_add);

        //Define what happens when the add button is clicked
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookLibrary bookLibrary = new BookLibrary
                        (-1, et_titleName.getText().toString(),
                                et_authorName.getText().toString(),
                                et_bookSynopsis.getText().toString());

                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddActivity.this);
                boolean success = dataBaseHelper.addOne(bookLibrary);
                //Data inserted successfully
                Toast.makeText(AddActivity.this, "Success " +success,  Toast.LENGTH_SHORT).show();
            }
        });
    }
}