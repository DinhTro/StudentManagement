package com.example.studentmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagement.database.database;

public class ActivityInfo extends AppCompatActivity {
    int idAccount = 0;
    TextView tvName;

    database database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_info);

        tvName = findViewById(R.id.tvName);

        database = new database(this);

        Cursor cursor = database.getNameAccount(idAccount);

        Intent intent = getIntent();
        idAccount = intent.getIntExtra("idAccount", 0);

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);

            tvName.setText(name);
        }
        cursor.moveToFirst();

        cursor.close();

    }
}
