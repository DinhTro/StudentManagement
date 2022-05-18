package com.example.studentmanagement.model;

public class Subject {
    private int id;

    private String subjectTitle;

    private int credits;

    private String time;

    private String place;

    public Subject(String subjectTitle, int credits, String time, String place) {
        this.subjectTitle = subjectTitle;
        this.credits = credits;
        this.time = time;
        this.place = place;
    }

    public Subject(int id, String subjectTitle, int credits, String time, String place) {
        this.id = id;
        this.subjectTitle = subjectTitle;
        this.credits = credits;
        this.time = time;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}