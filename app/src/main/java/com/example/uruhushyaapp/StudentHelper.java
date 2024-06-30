package com.example.uruhushyaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentHelper extends SQLiteOpenHelper{
    String Table_Name="student_table";
    String Field_Id="id";
    String Field_Names="names";
    String Field_RegNumber="regnumber";
    String Field_Program="program";
    String Field_Gender="gender";

    public StudentHelper(@Nullable Context context) {
        super(context, "uruhushyadb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+Table_Name+"("+Field_Id+" integer PRIMARY KEY AUTOINCREMENT,"+Field_Names+" text,"+Field_RegNumber+" text,"+Field_Program+" text,"+Field_Gender+" text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name);
        onCreate(db);

    }
    public void  addStudent(String names,String regnumber,String gender,String program) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Field_Names,names);
        contentValues.put(Field_RegNumber,regnumber);
        contentValues.put(Field_Gender,gender);
        contentValues.put(Field_Program,program);
        this.getWritableDatabase().insertOrThrow(Table_Name,null,contentValues);
    }
}
