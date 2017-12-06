package com.example.notepadby.cursproject.constants;

import com.example.notepadby.cursproject.entity.ListElement;

import java.util.Date;

/**
 * Created by NotePad.by on 06.12.2017.
 */

public class ConstantsClass implements Constants {
    public static void addElements(){
        for (int i = 0; i < 10; i++) {
            pastList.add(new ListElement(i,"Что было то прошло", "Hello", new Date()));
            todaysList.add(new ListElement(i,"Живи сегодняшним днем", "Greetings", new Date()));
            soonList.add(new ListElement(i,"Завтра - завтрашнее!", "Have a good day", new Date()));

        }
    }
}
