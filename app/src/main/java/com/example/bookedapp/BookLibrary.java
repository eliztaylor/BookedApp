package com.example.bookedapp;

public class BookLibrary {
    private int id;
    private String titleName, authorName, bookSynopsis;


    public BookLibrary( int id, String titleName, String authorName, String bookSynopsis) {
        this.id = id;
        this.titleName = titleName;
        this.authorName = authorName;
        this.bookSynopsis = bookSynopsis;

    }

    public BookLibrary(int i, String s, String string) {
    }

    @Override
    public String toString() {
        return "BookLibrary{" +
                "id=" + id +
                ", titleName='" + titleName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", bookSynopsis='" + bookSynopsis + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookSynopsis() {
        return bookSynopsis;
    }

    public void setBookSynopsis(String bookSynopsis) {
        this.bookSynopsis = bookSynopsis;
    }

}
