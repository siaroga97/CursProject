package com.example.notepadby.cursproject.constants;

import android.util.Log;
import com.example.notepadby.cursproject.entity.ListElement;
import java.util.Collections;
import java.util.Date;


public interface ListOperations extends Constants {

    static void updateLists() {
        pastList.clear();
        todaysList.clear();
        soonList.clear();

        for (ListElement element : globalList){
            if(new Date().getTime() - element.getDate().getTime() > Constants.ONE_DAY_MILLIS){
                pastList.add(element);
            }
            if(Math.abs(new Date().getTime() - element.getDate().getTime()) < Constants.ONE_DAY_MILLIS){
                todaysList.add(element);
            }
            if(element.getDate().getTime() - new Date().getTime() > Constants.ONE_DAY_MILLIS){
                soonList.add(element);
            }
        }
        Collections.sort(pastList, ListElement::compareTo);
        Collections.sort(todaysList, ListElement::compareTo);
        Collections.sort(soonList, ListElement::compareTo);
        Log.i(Constants.APP_LOGS, "Lists updated");
    }


    static void addTestElements() {
        for (int i = 0; i < 10; i++) {
            Date today = new Date();
            Date yesterday = new Date((long) (today.getTime() - 3600 * 1e3 * 25));
            Date tomorrow = new Date((long) (today.getTime() + 3600 * 1e3 * 25));

            globalList.add(new ListElement(i, "Что было то прошло", "Hello", yesterday));
            globalList.add(new ListElement(10 + i, "Живи сегодняшним днем", "Greetings", today));
            globalList.add(new ListElement(20 + i, "Завтра - завтрашнее!", "Have a good day", tomorrow));
        }
    }

    static void insertElement(ListElement element) {
        globalList.add(element);
    }
}
