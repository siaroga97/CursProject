package com.example.notepadby.cursproject.entity;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by NotePad.by on 06.12.2017.
 */

public class ListElement {
    private int id;
    private String title;
    private String description;
    private Date date;
    private GregorianCalendar dateEvent;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public GregorianCalendar getDateEvent() {
        return dateEvent;
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
}
