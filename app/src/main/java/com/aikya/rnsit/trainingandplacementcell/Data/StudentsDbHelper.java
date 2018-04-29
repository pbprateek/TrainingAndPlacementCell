package com.aikya.rnsit.trainingandplacementcell.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Cricket.db";
    private static final int DATABASE_VERSION = 1;

    public StudentsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `students` (\n" +
                "\t`_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`email`\tTEXT NOT NULL,\n" +
                "\t`password`\tTEXT NOT NULL,\n" +
                "\t`name`\tTEXT NOT NULL,\n" +
                "\t`contact`\tTEXT NOT NULL,\n" +
                "\t`branch`\tTEXT NOT NULL,\n" +
                "\t`marks10`\tTEXT DEFAULT 0,\n" +
                "\t`marks12`\tTEXT DEFAULT 0,\n" +
                "\t`marksbtech`\tTEXT DEFAULT 0,\n" +
                "\t`year10`\tTEXT DEFAULT 0,\n" +
                "\t`year12`\tTEXT DEFAULT 0,\n" +
                "\t`yearbtech`\tTEXT DEFAULT 0,\n" +
                "\t`placed`\tINTEGER DEFAULT 0\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + StudentInfo.StudentsEntry.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }



    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + StudentInfo.StudentsEntry.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();

                student.setEmail(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.EMAIL)));
                student.setPassword(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.PASSWORD)));
                student.setName(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.NAME)));
                student.setContact(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.CONTACT)));
                student.setBranch(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.BRANCH)));
                student.setMarks10(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKS10)));
                student.setMarks12(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKS12)));
                student.setMarksbtech(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKSBTECH)));
                student.setYear10(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEAR10)));
                student.setYear12(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEAR12)));
                student.setYearbtech(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEARBTECH)));
                student.setPlaced(cursor.getInt(cursor.getColumnIndex(StudentInfo.StudentsEntry.PLACED)));
                allStudents.add(student);


            } while (cursor.moveToNext());
        }
        db.close();
        return allStudents;
    }

    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + StudentInfo.StudentsEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public boolean updateStudent(String email,ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(StudentInfo.StudentsEntry.TABLE_NAME, values, StudentInfo.StudentsEntry.EMAIL + " = ? ", new String[] { email } );
        return true;
    }


    public long insertStudent(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(StudentInfo.StudentsEntry.TABLE_NAME, null, values);
        db.close();
        return id;
    }


    public Student getStudent(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM " + StudentInfo.StudentsEntry.TABLE_NAME + " WHERE " +
                StudentInfo.StudentsEntry.EMAIL + "=?", new String[]{email});
        Student student=new Student();

        if (cursor.moveToFirst()) {
            student.setEmail(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.EMAIL)));
            student.setPassword(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.PASSWORD)));
            student.setName(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.NAME)));
            student.setContact(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.CONTACT)));
            student.setBranch(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.BRANCH)));
            student.setMarks10(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKS10)));
            student.setMarks12(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKS12)));
            student.setMarksbtech(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKSBTECH)));
            student.setYear10(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEAR10)));
            student.setYear12(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEAR12)));
            student.setYearbtech(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEARBTECH)));
            student.setPlaced(cursor.getInt(cursor.getColumnIndex(StudentInfo.StudentsEntry.PLACED)));
        }


        return student;
    }

    public Integer deleteStudent(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(StudentInfo.StudentsEntry.TABLE_NAME,
                StudentInfo.StudentsEntry.EMAIL+ " = ? ",
                new String[] { email });
    }

    public Student getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM " + StudentInfo.StudentsEntry.TABLE_NAME + " WHERE " +
                StudentInfo.StudentsEntry._ID + "=?", new String[]{Integer.toString(id)});
        Student student = new Student();
        if (cursor.moveToFirst()) {


            student.setEmail(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.EMAIL)));
            student.setPassword(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.PASSWORD)));
            student.setName(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.NAME)));
            student.setContact(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.CONTACT)));
            student.setBranch(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.BRANCH)));
            student.setMarks10(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKS10)));
            student.setMarks12(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKS12)));
            student.setMarksbtech(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.MARKSBTECH)));
            student.setYear10(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEAR10)));
            student.setYear12(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEAR12)));
            student.setYearbtech(cursor.getString(cursor.getColumnIndex(StudentInfo.StudentsEntry.YEARBTECH)));
            student.setPlaced(cursor.getInt(cursor.getColumnIndex(StudentInfo.StudentsEntry.PLACED)));
        }
        return student;
    }



}
