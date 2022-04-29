package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSubject, btnAuthor, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubject = findViewById(R.id.btnSubject);
        btnAuthor = findViewById(R.id.btnAuthor);
        btnExit =  findViewById(R.id.btnExit);

        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivitySubject.class);
                startActivity(intent);
            }
        });

        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAuthor();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogExit();
            }
        });

    }

    private void dialogAuthor() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_info);
        dialog.show();
    }

    private void dialogExit() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_exit);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

//      Yes
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivitySignin.class);
                startActivity(intent);
//                Intent exit = new Intent(Intent.ACTION_MAIN);
//                exit.addCategory(Intent.CATEGORY_HOME);
//                startActivity(exit);
            }
        });
//      No

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}