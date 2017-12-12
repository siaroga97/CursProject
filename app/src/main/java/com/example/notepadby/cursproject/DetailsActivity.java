package com.example.notepadby.cursproject;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.notepadby.cursproject.constants.Constants;
import com.example.notepadby.cursproject.entity.ListElement;



public class DetailsActivity extends Activity {
    private EventDbHelper eventDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);


        TextView titleView = findViewById(R.id.detailTitle);
        TextView descriptionView = findViewById(R.id.detailDescription);
        TextView dateView = findViewById(R.id.detailDate);
        Integer id = getIntent().getIntExtra(Constants.INTENT_ID, 0);
        ListElement element=new ListElement();
        for(ListElement listElement:Constants.globalList){
            if(id==listElement.getId()){
                 element =listElement;
                break;
            }
        }


        titleView.setText(element.getTitle());
        descriptionView.setText(element.getDescription());
        dateView.setText(android.text.format.DateFormat.format("EEE. dd  MMMM yyyy HH:mm",element.getDate()));
    }
}
