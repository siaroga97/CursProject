package com.example.notepadby.cursproject.constants;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.notepadby.cursproject.entity.ListElement;

import java.util.Date;
import java.util.function.Predicate;


public interface ListOperations extends Constants {
    @RequiresApi(api = Build.VERSION_CODES.N)
    static void updateLists() {
        pastList.clear();
        todaysList.clear();
        soonList.clear();

        Predicate<ListElement> predicateYesterday;
        Predicate<ListElement> predicateToday;
        Predicate<ListElement> predicateTomorrow;

        predicateYesterday = element -> new Date().getTime() - element.getDate().getTime() > Constants.ONE_DAY_MILLIS;
        predicateToday = element -> Math.abs(new Date().getTime() - element.getDate().getTime()) < Constants.ONE_DAY_MILLIS;
        predicateTomorrow = element -> element.getDate().getTime() - new Date().getTime() > Constants.ONE_DAY_MILLIS;

        globalList.stream()
                .filter(predicateYesterday)
                .sorted()
                .forEachOrdered(pastList::add);
        globalList.stream()
                .filter(predicateToday)
                .sorted()
                .forEachOrdered(todaysList::add);
        globalList.stream()
                .filter(predicateTomorrow)
                .sorted()
                .forEachOrdered(soonList::add);
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
