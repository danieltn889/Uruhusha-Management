package com.example.uruhushyaapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class edit_activity extends AppCompatActivity {

    EditText editnames,editRegnumber,editProgram;
    RadioGroup radioGroup;
    StudentHelper studentHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editnames=findViewById(R.id.edit_names);
        editRegnumber=findViewById(R.id.edit_regnumber);
        editProgram=findViewById(R.id.editProgram);
        radioGroup=findViewById(R.id.gender);
        studentHelper=new StudentHelper(getApplicationContext());
        editStudent();


    }
    public  void  editStudent(){
        DisplayStudentRecordActivity object=new DisplayStudentRecordActivity();
        SharedPreferences sharedPreferences=getSharedPreferences(object.sp_id, Context.MODE_PRIVATE);
        String id=sharedPreferences.getString(studentHelper.Field_Id,"1");
        Cursor cursor=studentHelper.getReadableDatabase().rawQuery("SELECT * FROM "+studentHelper.Table_Name+" WHERE "+studentHelper.Field_Id+"=?",new String[]{id});
        if(cursor.moveToNext()){
            editnames.setText(cursor.getString(1));
            editRegnumber.setText(cursor.getString(2));
            editProgram.setText(cursor.getString(3));
        }
        //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();

    }

    public void updateData(View view) {
        String names=editnames.getText().toString();
        String reg=editRegnumber.getText().toString();
        String program=editProgram.getText().toString();
        ContentValues updateCV= new ContentValues();
        updateCV.put(studentHelper.Field_Names,names);
        updateCV.put(studentHelper.Field_RegNumber,reg);
        updateCV.put(studentHelper.Field_Program,program);
        DisplayStudentRecordActivity object=new DisplayStudentRecordActivity();
        SharedPreferences sharedPreferences=getSharedPreferences(object.sp_id, Context.MODE_PRIVATE);
        String id=sharedPreferences.getString(studentHelper.Field_Id,"1");
        studentHelper.getWritableDatabase().update(studentHelper.Table_Name,updateCV,"id=?",new  String[]{id});
        startActivity(new Intent(getApplicationContext(),DisplayStudentRecordActivity.class));

    }
}