package com.example.bookedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText et_titleUpdateName, et_authorUpdateName, et_bookUpdateSynopsis;
    Button btn_update;
    String id, title, author, synopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);



        et_titleUpdateName = findViewById(R.id.et_titleUpdateName);
        et_authorUpdateName = findViewById(R.id.et_authorUpdateName);
        et_bookUpdateSynopsis = findViewById(R.id.et_bookUpdateSynopsis);
        btn_update = findViewById(R.id.btn_Update);

        getandsetIntentData();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateActivity.this);
                title = et_titleUpdateName.getText().toString().trim();
                author = et_authorUpdateName.getText().toString().trim();
                synopsis = et_bookUpdateSynopsis.getText().toString().trim();
                dataBaseHelper.upDateData(id, title, author, synopsis);

            }
        });


    }

    void getandsetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")
                && getIntent().hasExtra("author") && getIntent().hasExtra("synopsis")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            synopsis = getIntent().getStringExtra("synopsis");

            et_titleUpdateName.setText(title);
            et_authorUpdateName.setText(author);
            et_bookUpdateSynopsis.setText(synopsis);
        } else {
            Toast.makeText(UpdateActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }
}