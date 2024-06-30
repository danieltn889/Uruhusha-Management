package com.example.uruhushyaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edit_name1,edit_regnumber1;
    RadioGroup rg_gender;
    StudentHelper studentHelper;
    ArrayAdapter<String> adapter;
    String the_program;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentHelper= new StudentHelper(getApplicationContext());
        spinner=findViewById(R.id.spinner);
        edit_name1=findViewById(R.id.edit_names);
        edit_regnumber1=findViewById(R.id.edit_regnumber);
        rg_gender=findViewById(R.id.gender);
        String[] program = new String[]{
                "IT",
                "ET",
                "RE",
                "MECH"
        };
        adapter= new ArrayAdapter<String>(this,androidx.appcompat.R.layout.
                support_simple_spinner_dropdown_item,program);
        spinner.setAdapter(adapter);

    }
    public void saveData(View view){
        String names= edit_name1.getText().toString();
        String regnumber=edit_regnumber1.getText().toString();
        the_program=spinner.getSelectedItem().toString();
        int gender=rg_gender.getCheckedRadioButtonId();
        RadioButton r_male=findViewById(R.id.gender_male);
        RadioButton f_male=findViewById(R.id.gender_female);
        String selected_gender="";
        if(gender==R.id.gender_male){
            selected_gender=r_male.getText().toString();
        }
        else{
            selected_gender=f_male.getText().toString();
        }
        studentHelper.addStudent(names,regnumber,selected_gender,the_program);
        Toast.makeText(getApplicationContext(),"Record saved",Toast.LENGTH_LONG).show();
    }

    public void viewStudent(View view) {
        startActivity(new Intent(getApplicationContext(),DisplayStudentRecordActivity.class));
    }
}