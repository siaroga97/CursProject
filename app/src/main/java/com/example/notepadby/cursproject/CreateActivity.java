package com.example.notepadby.cursproject;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepadby.cursproject.constants.Constants;
import com.example.notepadby.cursproject.constants.ListOperations;
import com.example.notepadby.cursproject.entity.ListElement;

import java.util.Date;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private Date addedDate;
    private String title;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        TextView createDate = findViewById(R.id.create_date);
        EditText titleView = findViewById(R.id.create_title);
        EditText descriptionView = findViewById(R.id.create_description);
        FloatingActionButton addButton = findViewById(R.id.create_button);

        createDate.setOnClickListener(listener -> {
            addedDate = new Date();
            createDate.setText(addedDate.toString());
        });

        addButton.setOnClickListener(listener -> {
            title = titleView.getText().toString();
            description = descriptionView.getText().toString();

            String result = checkAd(addedDate, title, description);
            Toast.makeText(CreateActivity.this, result, Toast.LENGTH_SHORT)
                    .show();
            if (result.equals(getString(R.string.ad_created))) {
                Log.i(Constants.APP_LOGS, getString(R.string.ad_created));
                setResult(Constants.REQUEST_CODE_LIST_UPDATED);
                new Handler().postDelayed(this::finish, 1000);
            }
        });
    }

    private String checkAd(Date date, String title, String description) {
        if (date != null && !title.isEmpty() && !description.isEmpty()) {
            createAd(Constants.globalList, date, title, description);
            return getString(R.string.ad_created);
        }
        return getString(R.string.incorrect_input);
    }

    private void createAd(List<ListElement> list, Date date, String title, String description) {
        ListOperations.insertElement(new ListElement(list.size(), title, description, date));
    }
}
