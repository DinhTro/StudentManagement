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
import com.example.studentmanagement.model.Subject;

public class ActivityUpdateStudent extends AppCompatActivity {

    EditText edtName, edtCode, edtBirthday;
    RadioButton rdoMale, rdoFemale;
    Button btnUpdateStudent;
    com.example.studentmanagement.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        edtName = findViewById(R.id.edtUpdateName);
        edtCode = findViewById(R.id.edtUpdateCode);
        edtBirthday = findViewById(R.id.edtUpdateBirthday);

        rdoMale = findViewById(R.id.rd0Male);
        rdoFemale = findViewById(R.id.rdoFemale);

        btnUpdateStudent = findViewById(R.id.btnUpdateStudent);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birth = intent.getStringExtra("birth");
        int idSubject = intent.getIntExtra("idSubject", 0);

        edtName.setText(name);
        edtCode.setText(code);
        edtBirthday.setText(birth);

        if (sex.equals("Male")) {
            rdoMale.setChecked(true);
        } else {
            rdoFemale.setChecked(true);
        }


        database = new database(this);
        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdate(id, idSubject);
            }
        });

    }

    private void dialogUpdate(int id, int idSubject) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_student);

        Button btnYes = dialog.findViewById(R.id.btnYesUpdateStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdateStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String code = edtCode.getText().toString().trim();
                String birth = edtBirthday.getText().toString().trim();

                Student student = createStudent();

                if (name.equals("") || code.equals("") || birth.equals("")) {

                    Toast.makeText(ActivityUpdateStudent.this, "did not enter enough info", Toast.LENGTH_SHORT).show();
                } else {

                    database.updateStudent(student, id);

                    Intent intent = new Intent(ActivityUpdateStudent.this, ActivityStudent.class);
                    intent.putExtra("idSubject", idSubject);
                    startActivity(intent);

                    Toast.makeText(ActivityUpdateStudent.this, "Successfully!", Toast.LENGTH_SHORT).show();
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

    private Student createStudent() {
        String name = edtName.getText().toString().trim();
        String sex = "";
        String code = edtCode.getText().toString().trim();
        String birthday = edtBirthday.getText().toString().trim();

        if (rdoMale.isChecked()) {
            sex = "Male";
        } else {
            sex = "Female";
        }

        Student student = new Student(name, sex, code, birthday);
        return student;
    }
}