package com.example.notepadby.cursproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Event> adapter;
    private ArrayAdapter<Event> pastAdapter;
    private ArrayAdapter<Event> futureAdapter;
    private ArrayAdapter<Event> todayAdapter;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        eventList = new ArrayList<>();
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar()));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar()));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(45, 10, 10)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(2015, 12, 30)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(2018, 1, 1)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(2019, 10, 2)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(2011, 10, 10)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(2013, 2, 2)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(2022, 2, 2)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(1999, 1, 1)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(2000, 2, 2)));
        eventList.add(new Event("sdsad", "dasd", new GregorianCalendar(1111, 1, 1)));
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, eventList);
        pastAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        futureAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        todayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DetailView.class);

                Event event = (Event) listView.getAdapter().getItem((int) id);
                String date = event.getDateEvent().get(Calendar.DAY_OF_MONTH) + " " + (event.getDateEvent().get(Calendar.MONTH) + 1) + " " + event.getDateEvent().get(Calendar.YEAR);

                intent.putExtra("title", event.getTitle());
                intent.putExtra("description", event.getDescription());
                intent.putExtra("date", date);


                startActivity(intent);

            }
        });
    }


    public void ClickPast(View view) {
        List<Event> past = new ArrayList<>();
        GregorianCalendar now = new GregorianCalendar();
        for (Event e : eventList) {
            if ((e.getDateEvent().before(now))) {
                past.add(e);
            }
        }
        pastAdapter.clear();
        pastAdapter.addAll(past);
        listView.setAdapter(pastAdapter);
    }

    public void ClickToday(View view) {
        List<Event> today = new ArrayList<>();
        GregorianCalendar now = new GregorianCalendar();
        for (Event e : eventList) {
            if (e.getDateEvent().get(Calendar.YEAR) == now.get(Calendar.YEAR)
                    && e.getDateEvent().get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
                    && e.getDateEvent().get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
                today.add(e);
            }
        }
        todayAdapter.clear();
        todayAdapter.addAll(today);
        listView.setAdapter(todayAdapter);
    }

    public void ClickSoon(View view) {
        List<Event> future = new ArrayList<>();
        GregorianCalendar now = new GregorianCalendar();
        for (Event e : eventList) {
            if ((e.getDateEvent().after(now))) {
                future.add(e);
            }
        }
        futureAdapter.clear();
        futureAdapter.addAll(future);
        listView.setAdapter(futureAdapter);
    }

    public void ClickAdd(View view) {
        adapter.addAll(eventList);
        listView.setAdapter(adapter);
    }

//    public void onClickListView(View view) {
//        int activePosition = 0; // первый элемент списка
//        listView.performItemClick(listView.getAdapter().
//                getView(activePosition, null, null), activePosition, listView.getAdapter().
//                getItemId(activePosition));
//    }
}
