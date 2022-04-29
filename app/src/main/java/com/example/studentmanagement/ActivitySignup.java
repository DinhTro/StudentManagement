package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.database;
import com.example.studentmanagement.model.Account;

public class ActivitySignup extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtEmail;
    Button btnSignin, btnSignup;

    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);

        btnSignin = findViewById(R.id.btnSignin);
        btnSignup = findViewById(R.id.btnSignup);

        database = new database(this);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySignup.this, ActivitySignin.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();

                Account account = CreateAccount();

                if (username.equals("") || password.equals("") || email.equals("")) {
                    Toast.makeText(ActivitySignup.this, "did not enter enough info", Toast.LENGTH_SHORT).show();
                } else {
                    database.addAccount(account);
                    Toast.makeText(ActivitySignup.this, "successful", Toast.LENGTH_SHORT).show();
                    edtUsername.setText("");
                    edtPassword.setText("");
                    edtEmail.setText("");
                }
            }
        });
    }

    private Account CreateAccount() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String email = edtEmail.getText().toString();

        Account account = new Account(username,password,email);
        return account;
    }
}