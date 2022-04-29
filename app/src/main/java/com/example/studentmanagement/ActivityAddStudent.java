package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Student;

public class ActivityAddStudent extends AppCompatActivity {

    Button btnAddStudent;
    EditText edtStudentName, edtCode, edtBirthday;
    RadioButton rdoMale, rdoFemale;
    com.example.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btnAddStudent = findViewById(R.id.btnAddStudent);

        edtStudentName = findViewById(R.id.edtStudentName);
        edtCode = findViewById(R.id.edtCode);
        edtBirthday = findViewById(R.id.edtBirthday);

        rdoMale = findViewById(R.id.rd0Male);
        rdoFemale = findViewById(R.id.rdoFemale);

        Intent intent = getIntent();
        int idSubject = intent.getIntExtra("idSubject", 0);

        database = new database(this);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd(idSubject);
            }
        });
    }

    private void DialogAdd(int idSubject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_student);

        Button btnYes = dialog.findViewById(R.id.btnYesAddStu);
        Button btnNo = dialog.findViewById(R.id.btnNoAddStu);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentName = edtStudentName.getText().toString().trim();
                String code =  edtCode.getText().toString().trim();
                String birthday = edtBirthday.getText().toString().trim();
                String sex = "";

                if (rdoMale.isChecked()) {
                    sex = "Male";
                } else {
                    sex = "Female";
                }

                if (studentName.equals("") || code.equals("") || sex.equals("") || birthday.equals("")) {

                    Toast.makeText(ActivityAddStudent.this, "did not enter enough info", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    Student student = CreateStudent(idSubject);

                    database.addStudent(student);

                    Intent intent = new Intent(ActivityAddStudent.this, ActivityStudent.class);
                    intent.putExtra("idSubject", idSubject);
                    startActivity(intent);

                    Toast.makeText(ActivityAddStudent.this, "Successfully!", Toast.LENGTH_SHORT).show();
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

    private Student CreateStudent(int idSubject) {
        String studentName = edtStudentName.getText().toString().trim();
        String code =  edtCode.getText().toString().trim();
        String sex = "";
        String birthday = edtBirthday.getText().toString().trim();

        if (rdoMale.isChecked()) {
            sex = "Male";
        } else {
            sex = "Female";
        }

        Student student = new Student(studentName, sex, code, birthday, idSubject);
        return student;
    }
}