package com.example.bookedapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewListActivity extends AppCompatActivity {
        CustomAdapter customAdapter;

        RecyclerView rv_booklist;
        DataBaseHelper dataBaseHelper;
        ArrayList<String> bookID, bookTitle, bookAuthor, bookSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        rv_booklist = findViewById(R.id.rv_bookList);


        dataBaseHelper = new DataBaseHelper(ViewListActivity.this);
        bookID = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookSynopsis = new ArrayList<>();


        storeDataInArrays();
        customAdapter = new CustomAdapter(ViewListActivity.this, this,  bookID, bookTitle, bookAuthor, bookSynopsis);
        rv_booklist.setAdapter(customAdapter);
        rv_booklist.setLayoutManager(new LinearLayoutManager(ViewListActivity.this));

    }
    void storeDataInArrays(){
        Cursor cursor = dataBaseHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(ViewListActivity.this, "No data in database", Toast.LENGTH_SHORT).show();
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