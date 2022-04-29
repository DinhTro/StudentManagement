package com.example.studentmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentmanagement.ActivityStudent;
import com.example.studentmanagement.R;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class adapterstudent extends BaseAdapter {

    private ActivityStudent context;

    private ArrayList<Student> arlStudent;


    public adapterstudent(ActivityStudent context, ArrayList<Student> arlStudent) {
        this.context = context;
        this.arlStudent = arlStudent;
    }

    @Override
    public int getCount() {
        return arlStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return arlStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_student, null);

        TextView tvStudentName = view.findViewById(R.id.tvStudentName);
        TextView tvCode = view.findViewById(R.id.tvCode);

        ImageButton studentInfo = view.findViewById(R.id.studentInfo);
        ImageButton studentEdit = view.findViewById(R.id.studentEdit);
        ImageButton studentDele = view.findViewById(R.id.studenttDele);

        Student student = arlStudent.get(i);

        tvCode.setText(student.getStudentCode());
        tvStudentName.setText(student.getStudentName());

        int id = student.getIdStudent();

        studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.info(id);
            }
        });


        studentEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.update(id);
            }
        });

        studentDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.delete(id);
            }
        });

        return view;
    }
}
