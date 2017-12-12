package com.example.notepadby.cursproject;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = EventDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "event.db";

    private static final int DATABASE_VERSION = 1;

    public EventDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + ShemeTable.EventEntity.TABLE_NAME + " ("
                + ShemeTable.EventEntity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ShemeTable.EventEntity.COLUMN_TITLE + " TEXT NOT NULL, "
                + ShemeTable.EventEntity.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + ShemeTable.EventEntity.COLUMN_DATE + " LONG );";

        db.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
