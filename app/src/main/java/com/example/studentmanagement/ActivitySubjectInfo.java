package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySubjectInfo extends AppCompatActivity {

    TextView tvTitle, tvCredit, tvTime, tvPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_info);

        tvTitle = findViewById(R.id.txtSubjectTitle);
        tvCredit = findViewById(R.id.txtSubjectCredit);
        tvTime = findViewById(R.id.txtSubjectTime);
        tvPlace = findViewById(R.id.txtSubjectPlace);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit", 0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        tvTitle.setText(title);
        tvCredit.setText(credit+"");
        tvTime.setText(time);
        tvPlace.setText(place);

    }
}