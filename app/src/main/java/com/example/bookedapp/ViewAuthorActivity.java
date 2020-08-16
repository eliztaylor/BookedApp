package com.example.bookedapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAuthorActivity extends AppCompatActivity {
        CustomAdapter customAdapter;

        RecyclerView rv_booklist;
        DataBaseHelper dataBaseHelper;
        ArrayList<String> bookID, bookTitle, bookAuthor, bookSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        rv_booklist = findViewById(R.id.rv_bookList);
        //lv_booklist = findViewById(R.id.lv_bookList);

        dataBaseHelper = new DataBaseHelper(ViewAuthorActivity.this);
        bookID = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookSynopsis = new ArrayList<>();


        storeDataInArrays();
        customAdapter = new CustomAdapter(ViewAuthorActivity.this, this, bookID, bookTitle, bookAuthor, bookSynopsis);
        rv_booklist.setAdapter(customAdapter);
        rv_booklist.setLayoutManager(new LinearLayoutManager(ViewAuthorActivity.this));


    }
    void storeDataInArrays(){
        Cursor cursor = dataBaseHelper.readAuthorData();
        if(cursor.getCount() == 0){
            Toast.makeText(ViewAuthorActivity.this, "No data in database", Toast.LENGTH_SHORT).show();
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