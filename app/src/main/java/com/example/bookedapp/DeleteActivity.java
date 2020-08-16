package com.example.bookedapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {
    EditText et_titleUpdateName, et_authorUpdateName, et_bookUpdateSynopsis;
    Button btn_Delete;
    String id, title, author, synopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);



        et_titleUpdateName = findViewById(R.id.et_titleUpdateName);
        et_authorUpdateName = findViewById(R.id.et_authorUpdateName);
        et_bookUpdateSynopsis = findViewById(R.id.et_bookUpdateSynopsis);
        btn_Delete = findViewById(R.id.btn_Delete);




        getandsetIntentData();

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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
            Toast.makeText(DeleteActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(DeleteActivity.this);
                dataBaseHelper.deleteOneRow(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}