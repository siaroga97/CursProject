package com.example.notepadby.cursproject.constants;

import com.example.notepadby.cursproject.entity.ListElement;

import java.util.ArrayList;
import java.util.List;


public interface Constants {
    List<ListElement> globalList = new ArrayList<>();

    List<ListElement> pastList = new ArrayList<>();
    List<ListElement> todaysList = new ArrayList<>();
    List<ListElement> soonList = new ArrayList<>();

    String APP_LOGS = "COM.EXAMPLE.NOTEPADBY";
    String INTENT_ID = "INTENT_ID";

    int REQUEST_CODE_LIST_UPDATED = 402;
    Long ONE_DAY_MILLIS = 1000 * 3600 * 24L;
}
