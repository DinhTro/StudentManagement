package com.example.studentmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.studentmanagement.ActivitySubject;
import com.example.studentmanagement.R;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class adaptersubject extends BaseAdapter {

    private ActivitySubject context;

    private ArrayList<Subject> ArrayListSubject;

    public adaptersubject(ActivitySubject context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListSubject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_subject, null);
        TextView tvSubjectTitle = view.findViewById(R.id.tvSubjectTitle);
        TextView tvCredit = view.findViewById(R.id.tvCredit);
        ImageButton subjectInfo = view.findViewById(R.id.subjectInfo);
        ImageButton subjectEdit = view.findViewById(R.id.subjectEdit);
        ImageButton subjectDele = view.findViewById(R.id.subjectDele);

        Subject subject = ArrayListSubject.get(i);

        tvCredit.setText(subject.getCredits()+"");
        tvSubjectTitle.setText(subject.getSubjectTitle());

        int id = subject.getId();

        subjectInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.info(id);
            }
        });


        subjectEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.update(id);
            }
        });

        subjectDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.delete(id);
            }
        });

        return view;
    }
}
