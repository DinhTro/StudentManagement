package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.studentmanagement.adapter.adapterstudent;
import com.example.studentmanagement.adapter.adaptersubject;
import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvStudent;
    ArrayList<Student> arlStudent;
    com.example.studentmanagement.database.database database;
    com.example.studentmanagement.adapter.adapterstudent adapterstudent;

    int idSubject = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbarStudent);
        lvStudent = findViewById(R.id.lvStudent);

        Intent intent = getIntent();
        idSubject = intent.getIntExtra("idSubject", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        arlStudent = new ArrayList<>();
        arlStudent.clear();

        Cursor cursor = database.getDataStudent(idSubject);

        while (cursor.moveToNext()) {
            int idSub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);

            arlStudent.add(new Student(id, name, sex, code, birthday, idSub));
        }

        adapterstudent = new adapterstudent(ActivityStudent.this, arlStudent);
        lvStudent.setAdapter(adapterstudent);
        cursor.moveToFirst();
        cursor.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuAddStudent:
                Intent intent = new Intent(ActivityStudent.this, ActivityAddStudent.class);
                intent.putExtra("idSubject", idSubject);
                startActivity(intent);
                break;

//          back
            default:
                Intent intent1 = new Intent(ActivityStudent.this, ActivitySubject.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count >= 1) {
            Intent intent = new Intent(this, ActivitySubject.class);
            startActivity(intent);
            finish();
        }
    }

    public void info(final int pos) {

        Cursor cursor = database.getDataStudent(idSubject);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if(id == pos) {
                Intent intent = new Intent(ActivityStudent.this, ActivityStudentInfo.class);

                intent.putExtra("id", pos);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);

                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);



                startActivity(intent);
            }
        }
    }

    public void update(final int idStudent) {
        Cursor cursor = database.getDataStudent(idSubject);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if (id == idStudent) {
                Intent intent = new Intent(ActivityStudent.this, ActivityUpdateStudent.class);

                intent.putExtra("id", idStudent);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int idSubject = cursor.getInt(5);

                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birth", birthday);
                intent.putExtra("idSubject", idSubject);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void delete(final int idStudent) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete_student);

        Button btnYes = dialog.findViewById(R.id.btnYesDeleStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoDeleStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database.deleteStudent(idStudent);
                Intent intent = new Intent(ActivityStudent.this, ActivityStudent.class);
                intent.putExtra("idSubject", idSubject);
                startActivity(intent);
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
}