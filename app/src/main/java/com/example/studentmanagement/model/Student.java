package com.example.studentmanagement.model;

public class Student {

    private int idStudent;
    private String studentName;
    private String sex;
    private String studentCode;
    private String dateOfBirth;
    private int idSubject;

    public Student(String studentName, String sex, String studentCode, String dateOfBirth) {
        this.studentName = studentName;
        this.sex = sex;
        this.studentCode = studentCode;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(String studentName, String sex, String studentCode, String dateOfBirth, int idSubject) {
        this.studentName = studentName;
        this.sex = sex;
        this.studentCode = studentCode;
        this.dateOfBirth = dateOfBirth;
        this.idSubject = idSubject;
    }

    public Student(int idStudent, String studentName, String sex, String studentCode, String dateOfBirth, int idSubject) {
        this.idStudent = idStudent;
        this.studentName = studentName;
        this.sex = sex;
        this.studentCode = studentCode;
        this.dateOfBirth = dateOfBirth;
        this.idSubject = idSubject;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }
}
