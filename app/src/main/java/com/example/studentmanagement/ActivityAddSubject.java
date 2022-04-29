package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {

    Button btnAddSubject;
    EditText edtSubjectTitle, edtSubjectCredit, edtSubjectTime, edtSubjectPlace;
    com.example.studentmanagement.database.database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btnAddSubject = findViewById(R.id.btnAddSubject);
        edtSubjectTitle = findViewById(R.id.editTextSubjectTitle);
        edtSubjectCredit = findViewById(R.id.editTextCredit);
        edtSubjectTime = findViewById(R.id.editTextTime);
        edtSubjectPlace = findViewById(R.id.editTextPlace);

        database = new database(this);

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
    }


    private void DialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_sub);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subjectTitle = edtSubjectTitle.getText().toString().trim();
                String credit =  edtSubjectCredit.getText().toString().trim();
                String time = edtSubjectTime.getText().toString().trim();
                String place = edtSubjectPlace.getText().toString().trim();

                if (subjectTitle.equals("") || credit.equals("") || time.equals("") || place.equals("")) {

                    Toast.makeText(ActivityAddSubject.this, "did not enter enough info", Toast.LENGTH_SHORT).show();
                } else {
                    Subject subject = CreateSubject();

                    database.addSubject(subject);

                    Intent intent = new Intent(ActivityAddSubject.this, ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddSubject.this, "Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    private Subject CreateSubject() {
        String subjectTitle = edtSubjectTitle.getText().toString().trim();
        int credit =  Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();
        String place = edtSubjectPlace.getText().toString().trim();

        Subject subject = new Subject(subjectTitle, credit, time, place);
        return subject;
    }
}