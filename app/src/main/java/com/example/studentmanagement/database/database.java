package com.example.studentmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.studentmanagement.model.Account;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.Subject;

public class database extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "studentmanagement1";
    //  account
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    //Bảng môn học
    private static String TABLE_SUBJECTS = "subject";
    private static String ID_SUBJECTS = "idsubject";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";

    //Bảng sinh viên
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "sudentname";
    private static String SEX = "sex";
    private static String STUDENT_CODE = "studentcode";
    private static String DATE_OF_BIRTH = "dateofbirth";

    private Context context;

    //    create account and insert data
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT) ";

    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','email@gmail.com')";

    //Tạo bảng môn học
    private String SQLQuery3 = "CREATE TABLE "+ TABLE_SUBJECTS +" ( "+ID_SUBJECTS+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_TITLE+" TEXT, "
            +CREDITS+" INTEGER, "
            +TIME+" TEXT, "
            + PLACE+" TEXT, "
            +ID_TAI_KHOAN+") ";


    //Tạo bảng sinh viên
    private String SQLQuery4 = "CREATE TABLE "+ TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
            +STUDENT_NAME+" TEXT, "
            +SEX+" TEXT, "
            +STUDENT_CODE+" TEXT, "
            +DATE_OF_BIRTH+" TEXT, "
            +ID_SUBJECTS+" INTEGER , FOREIGN KEY ( "+ ID_SUBJECTS +" ) REFERENCES "+
            TABLE_SUBJECTS+"("+ID_SUBJECTS+"))";

    public database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery1);
        sqLiteDatabase.execSQL(SQLQuery2);
        sqLiteDatabase.execSQL(SQLQuery3);
        sqLiteDatabase.execSQL(SQLQuery4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //    account
    public Cursor getDataAccount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_TAIKHOAN, null);
        return cursor;
    }

    public Cursor getNameAccount(int idAccount) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_TAIKHOAN+" WHERE "+ID_TAI_KHOAN+"="+idAccount, null);
        return cursor;
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TEN_TAI_KHOAN, account.getUsername());
        values.put(MAT_KHAU, account.getPassword());
        values.put(EMAIL, account.getEmail());

        db.insert(TABLE_TAIKHOAN, null, values);

        db.close();

        Log.e("add account", "successfully!");
    }

    //subject
    //  lấy dữ liêu
    public Cursor getDataSubject() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SUBJECTS, null);
        return cursor;
    }
    //  Thêm
    public void addSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SUBJECT_TITLE, subject.getSubjectTitle());
        values.put(CREDITS, subject.getCredits());
        values.put(TIME, subject.getTime());
        values.put(PLACE, subject.getPlace());

        db.insert(TABLE_SUBJECTS, null, values);
        db.close();
    }
    //  Sữa
    public boolean updateSubject(Subject subject, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_TITLE, subject.getSubjectTitle());
        values.put(CREDITS, subject.getCredits());
        values.put(TIME, subject.getTime());
        values.put(PLACE, subject.getPlace());

        db.update(TABLE_SUBJECTS, values, ID_SUBJECTS + " = " + id, null);
        return true;
    }
    //  Xóa
    public int deleteSubject(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int remove = db.delete(TABLE_SUBJECTS, ID_SUBJECTS+" = "+i, null);
        return remove;
    }


    public int deleteSubjectStudent(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int remove = db.delete(TABLE_SUBJECTS, ID_SUBJECTS+" = "+i, null);
        return remove;
    }


    //    student methods
//Add
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(STUDENT_NAME, student.getStudentName());
        values.put(SEX, student.getSex());
        values.put(STUDENT_CODE, student.getStudentCode());
        values.put(DATE_OF_BIRTH, student.getDateOfBirth());
        values.put(ID_SUBJECTS, student.getIdSubject());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }
    //Get data
    public Cursor getDataStudent(int idSubject) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_STUDENT+" WHERE "+ID_SUBJECTS+"="+idSubject, null);
        return cursor;
    }
    //remove
    public int deleteStudent(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int remove = db.delete(TABLE_STUDENT, ID_STUDENT+" = "+i, null);
        return remove;
    }

    //    update
    public boolean updateStudent(Student student, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getStudentName());
        values.put(SEX, student.getSex());
        values.put(STUDENT_CODE, student.getStudentCode());
        values.put(DATE_OF_BIRTH, student.getDateOfBirth());

        db.update(TABLE_STUDENT, values, ID_STUDENT+" = "+id, null);
        return true;
    }
}