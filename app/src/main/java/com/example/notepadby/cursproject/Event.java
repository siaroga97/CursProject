package com.example.notepadby.cursproject;

import java.util.Calendar;

import java.util.GregorianCalendar;

public class Event {
    private GregorianCalendar dateEvent;
    private String title;
    private String description;
    private static int count=0;
    private int id;

    Event() {

    }

    Event(String title, String description, GregorianCalendar date) {

        this.id=count++;
        this.title = title;
        this.description = description;
        this.dateEvent = date;
    }

    public  int getId() {
        return id;
    }

    public GregorianCalendar getDateEvent() {
        return dateEvent;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDateEvent(GregorianCalendar dateEvent) {
        this.dateEvent = dateEvent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id+" "+title + "\n" + dateEvent.get(Calendar.DAY_OF_MONTH) + " " + (dateEvent.get(Calendar.MONTH) + 1) + " " + dateEvent.get(Calendar.YEAR);
    }
}
