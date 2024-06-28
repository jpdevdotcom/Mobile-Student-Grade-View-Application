package com.example.bsit_3a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataAccess extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "StudentApp";

    Context _context;

    SQLiteDatabase schoolYear;

    public DataAccess(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;

        schoolYear = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE SchoolYear (SchoolYrId INTEGER PRIMARY KEY AUTOINCREMENT,SchoolYearStart TEXT NOT NULL, SchoolYearEnd TEXT NOT NULL, Semester TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<SchoolYear> GetSchoolYears() {
        List<SchoolYear> sys = new ArrayList<>();

        String query = "SELECT * FROM SCHOOLYEAR";
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();

        SchoolYear sy;

        while (!cursor.isAfterLast()){

            sy = new SchoolYear();
            sy.SchoolYearStart = cursor.getString(1);
            sy.SchoolYearEnd = cursor.getString(2);
            sy.SchoolYearSemester = cursor.getString(3);
            sy.id = cursor.getInt(0);
            sy.SYId = cursor.getInt(4);

            sys.add(sy);

            cursor.moveToNext();
        }

        return sys;
    }

    public void SchoolYearAdd(SchoolYear schoolYear)
    {
        ContentValues values = new ContentValues();
        values.put("SchoolYearStart", schoolYear.SchoolYearStart);
        values.put("SchoolYearEnd", schoolYear.SchoolYearEnd);
        values.put("Semester", schoolYear.SchoolYearSemester);
        values.put("SYId", schoolYear.SYId);

        getWritableDatabase().insert("SchoolYear", null, values);
    }

    public void SchoolYearDeleteAll()
    {
        getWritableDatabase().delete("SchoolYear", null, null);
    }

    public List<Grade> getGrades(int syid) {
        Cursor cursor = getReadableDatabase().rawQuery("select GradeId, SubjectCode, SubjectDescription, Grade, SYId from Grades" +
                " where SyId='" + syid + "'", null);
        cursor.moveToFirst();

        List<Grade> grades = new ArrayList<>();

        Grade gd;

        while (!cursor.isAfterLast())
        {
            gd = new Grade();
            gd.Grade = cursor.getString(3);
            gd.SubjectDescription = cursor.getString(2);
            gd.SubjectCode = cursor.getString(1);
            gd.SyId = cursor.getInt(4);

            grades.add(gd);

            cursor.moveToNext();
        }

        cursor.close();

        return grades;
    }

    public void GradeDeleteAll()
    {
        getWritableDatabase().delete("Grades", null, null);
    }

    public void GradeAdd( Grade grade) {
        ContentValues values = new ContentValues();
        values.put("SubjectCode", grade.SubjectCode);
        values.put("SubjectDescription", grade.SubjectDescription);
        values.put("Grade", grade.Grade);
        values.put("SYId", grade.SyId);

        getWritableDatabase().insert("Grades", null, values);
    }

}
