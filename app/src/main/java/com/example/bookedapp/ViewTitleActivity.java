package com.example.bookedapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewTitleActivity extends AppCompatActivity {
        CustomAdapter customAdapter;

        RecyclerView rv_booklist;

        DataBaseHelper dataBaseHelper;
        ArrayList<String> bookID, bookTitle, bookAuthor, bookSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        rv_booklist = findViewById(R.id.rv_bookList);

        dataBaseHelper = new DataBaseHelper(ViewTitleActivity.this);
        bookID = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookSynopsis = new ArrayList<>();


        storeDataInArrays();
        customAdapter = new CustomAdapter(ViewTitleActivity.this, this, bookID, bookTitle, bookAuthor, bookSynopsis);
        rv_booklist.setAdapter(customAdapter);
        rv_booklist.setLayoutManager(new LinearLayoutManager(ViewTitleActivity.this));



    }
    void storeDataInArrays(){
        Cursor cursor = dataBaseHelper.readTitleData();
        if(cursor.getCount() == 0){
            Toast.makeText(ViewTitleActivity.this, "No data in database", Toast.LENGTH_SHORT).show();
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