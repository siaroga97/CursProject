package com.example.notepadby.cursproject.constants;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.notepadby.cursproject.EventDbHelper;
import com.example.notepadby.cursproject.ShemeTable;
import com.example.notepadby.cursproject.entity.ListElement;

import java.util.Collections;
import java.util.Date;


public interface ListOperations extends Constants {

    static void updateLists() {
        pastList.clear();
        todaysList.clear();
        soonList.clear();

        for (ListElement element : globalList) {
            if (new Date().getTime() - element.getDate().getTime() > Constants.ONE_DAY_MILLIS) {
                pastList.add(element);
            }
            if (Math.abs(new Date().getTime() - element.getDate().getTime()) < Constants.ONE_DAY_MILLIS) {
                todaysList.add(element);
            }
            if (element.getDate().getTime() - new Date().getTime() > Constants.ONE_DAY_MILLIS) {
                soonList.add(element);
            }
        }
        Collections.sort(pastList, ListElement::compareTo);
        Collections.sort(todaysList, ListElement::compareTo);
        Collections.sort(soonList, ListElement::compareTo);
        Log.i(Constants.APP_LOGS, "Lists updated");
    }


    static void addTestElements(EventDbHelper eventDbHelper) {

        SQLiteDatabase db = eventDbHelper.getReadableDatabase();
        String[] projection = {
                ShemeTable.EventEntity._ID,
                ShemeTable.EventEntity.COLUMN_TITLE,
                ShemeTable.EventEntity.COLUMN_DESCRIPTION,
                ShemeTable.EventEntity.COLUMN_DATE};


        Cursor cursor = db.query(
                ShemeTable.EventEntity.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            int idColumnIndex = cursor.getColumnIndex(ShemeTable.EventEntity._ID);
            int titleColumnIndex = cursor.getColumnIndex(ShemeTable.EventEntity.COLUMN_TITLE);
            int descripritonColumnIndex = cursor.getColumnIndex(ShemeTable.EventEntity.COLUMN_DESCRIPTION);
            int dateColumnIndex = cursor.getColumnIndex(ShemeTable.EventEntity.COLUMN_DATE);


            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(titleColumnIndex);
                String currentDescription = cursor.getString(descripritonColumnIndex);
                Date currentDate = new Date(cursor.getLong(dateColumnIndex));

                globalList.add(new ListElement(currentID, currentTitle, currentDescription, currentDate));

            }
        } finally {

            cursor.close();
        }
    }
//        for (int i = 0; i < 10; i++) {
//            Date today = new Date();
//            Date yesterday = new Date((long) (today.getTime() - 3600 * 1e3 * 25));
//            Date tomorrow = new Date((long) (today.getTime() + 3600 * 1e3 * 25));
//
//            globalList.add(new ListElement(i, "Что было то прошло", "Hello", yesterday));
//            globalList.add(new ListElement(10 + i, "Живи сегодняшним днем", "Greetings", today));
//            globalList.add(new ListElement(20 + i, "Завтра - завтрашнее!", "Have a good day", tomorrow));
//        }


    static void insertElement(ListElement element) {
        globalList.add(element);
    }
}
