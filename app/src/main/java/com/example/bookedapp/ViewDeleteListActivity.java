package com.example.bookedapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewDeleteListActivity extends AppCompatActivity {
        CustomAdapterClick customAdapter;

        RecyclerView rv_booklist;
        DataBaseHelper dataBaseHelper;
        ArrayList<String> bookID, bookTitle, bookAuthor, bookSynopsis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_list);

        rv_booklist = findViewById(R.id.rv_bookList);

        dataBaseHelper = new DataBaseHelper(ViewDeleteListActivity.this);
        bookID = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookSynopsis = new ArrayList<>();


        storeDataInArrays();
        customAdapter = new CustomAdapterClick(ViewDeleteListActivity.this, this, bookID, bookTitle, bookAuthor, bookSynopsis);
        rv_booklist.setAdapter(customAdapter);
        rv_booklist.setLayoutManager(new LinearLayoutManager(ViewDeleteListActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = dataBaseHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(ViewDeleteListActivity.this, "No data in database", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                bookID.add(cursor.getString(0));
                bookTitle.add(cursor.getString(1));
                bookAuthor.add(cursor.getString(2));
                bookSynopsis.add(cursor.getString(3));


            }
        }

    }


}