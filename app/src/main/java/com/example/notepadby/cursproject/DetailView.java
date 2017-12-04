package com.example.notepadby.cursproject;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class DetailView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        TextView titleView = findViewById(R.id.detailTitle);
        TextView descriptionView = findViewById(R.id.detailDescription);
        TextView dateView = findViewById(R.id.detailDate);

        String title = getIntent().getStringExtra("title");

         String description = getIntent().getStringExtra("description");
          String date = getIntent().getStringExtra("date");
        titleView.setText(title);
          descriptionView.setText(description);
          dateView.setText(date);



    }
}
