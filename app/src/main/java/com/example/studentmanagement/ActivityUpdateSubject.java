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

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtTitle, edtCredit, edtTime, edtPlace;
    Button btnUpdate;
    com.example.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtTitle = findViewById(R.id.edtUpdateSubTitle);
        edtCredit = findViewById(R.id.edtUpdateCredit);
        edtTime = findViewById(R.id.edtUpdateTime);
        edtPlace = findViewById(R.id.edtUpdatePlace);
        btnUpdate = findViewById(R.id.btnUpdateSubject);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit", 0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edtTitle.setText(title);
        edtCredit.setText(credit+"");
        edtTime.setText(time);
        edtPlace.setText(place);


        database = new database(this);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdate(id);
            }
        });
    }

    private void dialogUpdate(int id) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_subject);

        Button btnYes = dialog.findViewById(R.id.btnYesUpdateSub);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdateSub);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectTitle = edtTitle.getText().toString().trim();
                String credit =  edtCredit.getText().toString().trim();
                String time = edtTime.getText().toString().trim();
                String place = edtPlace.getText().toString().trim();

                if (subjectTitle.equals("") || credit.equals("") || time.equals("") || place.equals("")) {

                    Toast.makeText(ActivityUpdateSubject.this, "did not enter enough info", Toast.LENGTH_SHORT).show();
                } else {
                    Subject subject = UpdateSubject();

                    database.updateSubject(subject, id);

                    Intent intent = new Intent(ActivityUpdateSubject.this, ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityUpdateSubject.this, "Successfully!", Toast.LENGTH_SHORT).show();
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

    private Subject UpdateSubject() {
        String subjectTitle = edtTitle.getText().toString().trim();
        int credit =  Integer.parseInt(edtCredit.getText().toString().trim());
        String time = edtTime.getText().toString().trim();
        String place = edtPlace.getText().toString().trim();

        Subject subject = new Subject(subjectTitle, credit, time, place);
        return subject;
    }
}