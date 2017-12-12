package com.example.notepadby.cursproject;


import android.provider.BaseColumns;

public  final class ShemeTable {
    private ShemeTable(){

    }
    public static final class EventEntity implements BaseColumns{
        public final static String TABLE_NAME = "events";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TITLE = "title";
        public final static String COLUMN_DESCRIPTION = "description";
        public final static String COLUMN_DATE = "date";

    }
}
