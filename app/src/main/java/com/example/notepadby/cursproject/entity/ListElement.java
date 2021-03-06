package com.example.notepadby.cursproject.entity;

import android.support.annotation.NonNull;

import java.util.Date;



public class ListElement implements Comparable {
    private int id;
    private String title;
    private String description;
    private Date date;

    public ListElement(){
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }



    public Date getDate() {
        return date;
    }

    public ListElement(int id, String title, String description, Date date) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return (int) (((ListElement) o).getDate().getTime() - this.getDate().getTime());
    }
}
