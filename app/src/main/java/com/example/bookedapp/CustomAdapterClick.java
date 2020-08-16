package com.example.bookedapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterClick extends RecyclerView.Adapter<CustomAdapterClick.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList bookId, bookTitle, bookAuthor, bookSynopsis;


    CustomAdapterClick(Activity activity, Context context, ArrayList bookId, ArrayList bookTitle, ArrayList bookAuthor, ArrayList bookSynopsis){
        this.activity = activity;
        this.context = context;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookSynopsis = bookSynopsis;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.rvbookID.setText(String.valueOf(bookId.get(position)));
        holder.rvbookTitle.setText(String.valueOf(bookTitle.get(position)));
        holder.rvbookAuthor.setText(String.valueOf(bookAuthor.get(position)));
        holder.rvbookSynopsis.setText(String.valueOf(bookSynopsis.get(position)));
        holder.rvrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeleteActivity.class);
                intent.putExtra("id", String.valueOf(bookId.get(position)));
                intent.putExtra("title", String.valueOf(bookTitle.get(position)));
                intent.putExtra("author", String.valueOf(bookAuthor.get(position)));
                intent.putExtra("synopsis", String.valueOf(bookSynopsis.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });




    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rvbookID, rvbookTitle, rvbookAuthor, rvbookSynopsis;
        RelativeLayout rvrow;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rvbookID = itemView.findViewById(R.id.rvbookID);
            rvbookTitle = itemView.findViewById(R.id.rvbookTitle);
            rvbookAuthor = itemView.findViewById(R.id.rvbookAuthor);
            rvbookSynopsis = itemView.findViewById(R.id.rvbookSynopsis);
            rvrow = itemView.findViewById(R.id.rvrow);



        }
    }
}
