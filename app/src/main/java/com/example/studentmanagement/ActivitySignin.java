package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.database;

public class ActivitySignin extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnSignin, btnSignup;

    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnSignup = findViewById(R.id.btnSignup);

        database = new database(this);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                Cursor cursor = database.getDataAccount();

                while (cursor.moveToNext()) {
                    String dbUsername = cursor.getString(1);
                    String dbPassword =  cursor.getString(2);

                    if (dbUsername.equals(username) && dbPassword.equals(password)) {
                        int id = cursor.getInt(0);
                        String user = cursor.getString(1);
                        Intent intent = new Intent(ActivitySignin.this, MainActivity.class);

                        intent.putExtra("id", id);
                        intent.putExtra("username", user);

                        startActivity(intent);
                    } else {
//                        Toast.makeText(ActivitySignin.this, "username or password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.moveToFirst();
                cursor.close();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySignin.this, ActivitySignup.class);
                startActivity(intent);
            }
        });

    }
}