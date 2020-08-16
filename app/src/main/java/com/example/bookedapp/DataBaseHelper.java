package com.example.bookedapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "BOOKED_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BOOK_AUTHOR = "BOOK_AUTHOR";
    public static final String COLUMN_BOOK_TITLE = "BOOK_TITLE";
    public static final String COLUMN_BOOK_SYNOPSIS = "BOOK_SYNOPSIS";
    private Context context;


    public DataBaseHelper(@Nullable Context context){
            super(context, "books.db", null, 1);
            this.context = context;
        }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BOOK_TITLE + " TEXT, "
                + COLUMN_BOOK_AUTHOR + " TEXT, "
                + COLUMN_BOOK_SYNOPSIS + " TEXT )";



        db.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);


    }
    public boolean addOne(BookLibrary bookLibrary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK_TITLE,bookLibrary.getTitleName());
        cv.put(COLUMN_BOOK_AUTHOR, bookLibrary.getAuthorName());
        cv.put(COLUMN_BOOK_SYNOPSIS, bookLibrary.getBookSynopsis());


        long insert = db.insert(TABLE_NAME, null, cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }

    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAuthorData(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM BOOKED_TABLE GROUP BY BOOK_AUTHOR", null);
        }
        return cursor;
    }

    Cursor readTitleData(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM BOOKED_TABLE GROUP BY BOOK_TITLE", null);
        }
        return cursor;
    }

    void upDateData(String row_id, String title, String author, String synopsis){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BOOK_TITLE, title);
        cv.put(COLUMN_BOOK_AUTHOR, author);
        cv.put(COLUMN_BOOK_SYNOPSIS, synopsis);

        long result = db.update(TABLE_NAME, cv, "id = ?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id = ?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();
        }
    }


}
