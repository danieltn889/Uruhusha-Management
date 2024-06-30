package com.example.uruhushyaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DisplayStudentRecordActivity extends AppCompatActivity {

    ListView list;
    StudentHelper studentHelper;
    StudentAdapter studentAdapter;
    ArrayList<String> a_id=new ArrayList<String>();
    ArrayList<String> a_names=new ArrayList<String>();
    ArrayList<String> a_regno=new ArrayList<String>();
    ArrayList<String> a_gender=new ArrayList<String>();
    ArrayList<String> a_program=new ArrayList<String>();
    String sp_id="edit_sharedPreference";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student_record);
        list=findViewById(R.id.list_student);
        studentHelper=new StudentHelper(getApplicationContext());
        displayStudent();
    }

    public void displayStudent(){
        a_id.clear();
        a_names.clear();
        a_regno.clear();
        a_gender.clear();
        a_program.clear();
        Cursor cursor=studentHelper.getReadableDatabase().rawQuery("SELECT * FROM "+studentHelper.Table_Name,null);
        if(cursor.moveToNext()){
            while (cursor.moveToNext()){
                a_id.add(cursor.getString(0));
                a_names.add(cursor.getString(1));
                a_regno.add(cursor.getString(2));
                a_program.add(cursor.getString(3));
                a_gender.add(cursor.getString(4));

            }
        }
        studentAdapter= new StudentAdapter(getApplicationContext(),a_id,a_names,a_regno,a_program,a_gender);
        list.setAdapter(studentAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(), list);
                    popupMenu.getMenuInflater().inflate(R.menu.student_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId()==R.id.menu_item_edit){
                                sharedPreferences=getSharedPreferences(sp_id, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString(studentHelper.Field_Id,a_id.get(position));
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(),edit_activity.class));
                                Toast.makeText(getApplicationContext(),"Edit record",Toast.LENGTH_LONG).show();

                            }
                            else {
                                deleteRecordMethod(a_id.get(position));
                                //Toast.makeText(getApplicationContext(),"Delete record with id"+a_id.get(position),Toast.LENGTH_LONG).show();
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
            }
        });
    }
    public  void  deleteRecordMethod(String id){
        studentHelper.getWritableDatabase().delete(studentHelper.Table_Name,"id=?",new String[]{id});
        displayStudent();
    }
}
