package com.example.bookedapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
   Button btn_add, btn_viewList, btn_updateList, btn_deleteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewList = findViewById(R.id.btn_viewList);
        btn_updateList = findViewById(R.id.btn_updateList);
        btn_deleteList = findViewById(R.id.btn_deleteList);

        btn_deleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deleteintent = new Intent(MainActivity.this, ViewDeleteListActivity.class);
                startActivity(deleteintent);
            }
        });

        btn_updateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateintent = new Intent(MainActivity.this,ViewUpdateListActivity.class);
                startActivity(updateintent);
            }
        });



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
            }
    //Show menu to allow user to decide how to display results
    public void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        popupMenu.setOnMenuItemClickListener(MainActivity.this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.byID:
                Intent intent = new Intent(MainActivity.this, ViewListActivity.class);
                startActivity(intent);
                return true;
            case R.id.byAuthor:
                Intent authintent = new Intent(MainActivity.this, ViewAuthorActivity.class);
                startActivity(authintent);
                return true;

            case R.id.byTitle:
                Intent titleintent = new Intent(MainActivity.this, ViewTitleActivity.class);
                startActivity(titleintent);
                return true;

            default:
                return false;
        }
    }
}