package com.example.notepadby.cursproject;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepadby.cursproject.constants.Constants;
import com.example.notepadby.cursproject.constants.ListOperations;
import com.example.notepadby.cursproject.entity.ListElement;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CreateActivity extends AppCompatActivity implements TimePickerFragment.TimePickedListener, DatePickerFragment.DatePickedListener {


    private String title;
    private String description;
    private EventDbHelper eventDbHelper;

    private int mHour;
    private int mMinute;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        eventDbHelper = new EventDbHelper(this);
        TextView createDate = findViewById(R.id.create_date);
        TextView createTime = findViewById(R.id.create_time);
        EditText titleView = findViewById(R.id.create_title);
        EditText descriptionView = findViewById(R.id.create_description);
        FloatingActionButton addButton = findViewById(R.id.create_button);
        createTime.setOnClickListener(listener -> {
            DialogFragment timeFragment = new TimePickerFragment();
            timeFragment.show(getSupportFragmentManager(), "timePicker");
        });
        createDate.setOnClickListener(listener -> {
            DialogFragment dateFragment = new DatePickerFragment();
            dateFragment.show(getSupportFragmentManager(), "datePicker");
        });


        addButton.setOnClickListener(listener -> {
            title = titleView.getText().toString();
            description = descriptionView.getText().toString();
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth - 1, mDay, mHour, mMinute);
            String result = checkAd(calendar.getTime(), title, description);


            Toast.makeText(CreateActivity.this, result, Toast.LENGTH_SHORT)
                    .show();
            if (result.equals(getString(R.string.ad_created))) {
                Log.i(Constants.APP_LOGS, getString(R.string.ad_created));
                setResult(Constants.REQUEST_CODE_LIST_UPDATED);
                new Handler().postDelayed(this::finish, 1000);
            }
        });
    }

    public void onTimePicked(Calendar time) {
        mHour = time.get(Calendar.HOUR_OF_DAY);
        mMinute = time.get(Calendar.MINUTE);
        updateDisplay();
    }

    public void onDatePicked(Calendar date) {
        mYear = date.get(Calendar.YEAR);
        mMonth = date.get(Calendar.MONTH) + 1;
        mDay = date.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void updateDisplay() {
        if (mHour != 0 && mMinute != 0) {
            TextView createDate = findViewById(R.id.create_time);
            createDate.setText(new StringBuilder().append(pad(mHour)).append(":")
                    .append(pad(mMinute)));
        }
        if (mDay != 0 && mMonth != 0 && mYear != 0) {
            TextView creatime = findViewById(R.id.create_date);
            creatime.setText(new StringBuilder().append(mDay).append(".")
                    .append(mMonth).append(".").append(mYear));
        }
    }

    private String checkAd(Date date, String title, String description) {
        if (date != null && !title.isEmpty() && !description.isEmpty()) {
            createAd(Constants.globalList, date, title, description);
            return getString(R.string.ad_created);
        }
        return getString(R.string.incorrect_input);
    }

    private void createAd(List<ListElement> list, Date date, String title, String description) {
        SQLiteDatabase db = eventDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ShemeTable.EventEntity.COLUMN_TITLE, title);
        values.put(ShemeTable.EventEntity.COLUMN_DESCRIPTION, description);
        values.put(ShemeTable.EventEntity.COLUMN_DATE, date.getTime());
        long newRowId = db.insert(ShemeTable.EventEntity.TABLE_NAME, null, values);
        ListOperations.insertElement(new ListElement(list.size() + 1, title, description, date));
    }
}
