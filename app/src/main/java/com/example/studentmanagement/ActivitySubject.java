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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.studentmanagement.adapter.adaptersubject;
import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvSubject;
    ArrayList<Subject> arlSubject;
    com.example.studentmanagement.database.database database;
    com.example.studentmanagement.adapter.adaptersubject adaptersubject;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        lvSubject = findViewById(R.id.lvSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        arlSubject = new ArrayList<>();


        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            arlSubject.add(new Subject(id, title, credit, time, place));
        }

            adaptersubject = new adaptersubject(ActivitySubject.this, arlSubject);
            lvSubject.setAdapter(adaptersubject);
            cursor.moveToFirst();
            cursor.close();

            lvSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(ActivitySubject.this, ActivityStudent.class);
                    int idSubject = arlSubject.get(i).getId();
                    intent.putExtra("idSubject", idSubject);
                    startActivity(intent);
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddsub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuAdd:
                Intent intent1 = new Intent(ActivitySubject.this, ActivityAddSubject.class);
                startActivity(intent1);
                break;

//          back
            default:
                Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

//    click back > main
    @Override
    public void onBackPressed() {
        count++;
        if (count >= 1) {
            Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }



    public void info(final int pos) {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if(id == pos) {
                Intent intent = new Intent(ActivitySubject.this, ActivitySubjectInfo.class);

                intent.putExtra("id", id);
                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title", title);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);

                startActivity(intent);
            }
        }
    }

    public void delete(final int pos) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete_subject);

        Button btnYes = dialog.findViewById(R.id.btnYesDeleSub);
        Button btnNo = dialog.findViewById(R.id.btnNoDeleSub);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database.deleteSubject(pos);
                database.deleteSubjectStudent(pos);
                Intent intent = new Intent(ActivitySubject.this, ActivitySubject.class);
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

    public void update(final int pos) {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if (id == pos) {
                Intent intent = new Intent(ActivitySubject.this, ActivityUpdateSubject.class);


                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);

                startActivity(intent);
            }
        }
    }
}