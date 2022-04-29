package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityStudentInfo extends AppCompatActivity {

    TextView txtName, txtSex, txtCode, txtBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        txtName = findViewById(R.id.txtName);
        txtSex = findViewById(R.id.txtSex);
        txtCode = findViewById(R.id.txtCode);
        txtBirthday = findViewById(R.id.txtBirthday);
//get data
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");

//set data
        txtName.setText(name);
        txtSex.setText(sex);
        txtCode.setText(code);
        txtBirthday.setText(birthday);

    }
}