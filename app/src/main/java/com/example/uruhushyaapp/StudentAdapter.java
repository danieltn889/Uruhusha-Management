package com.example.uruhushyaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class StudentAdapter extends BaseAdapter {

    ArrayList<String> a_id=new ArrayList<String>();
    ArrayList<String> a_names=new ArrayList<String>();
    ArrayList<String> a_regno=new ArrayList<String>();
    ArrayList<String> a_gender=new ArrayList<String>();
    ArrayList<String> a_program=new ArrayList<String>();
    Context context;
    StudentAdapter(Context context, ArrayList<String> a_id,ArrayList<String> a_names,ArrayList<String> a_regno,ArrayList<String> a_gender,ArrayList<String> a_program){

        this.context=context;
        this.a_id=a_id;
        this.a_names=a_names;
        this.a_regno=a_regno;
        this.a_gender=a_gender;
        this.a_program=a_program;
    }
    /* focus on the getcount() and getview()*/
    @Override
    public int getCount() {
        return a_id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentViewHolder studentViewHolder;
        LayoutInflater inflater;
        if (convertView==null){
            studentViewHolder= new StudentViewHolder();
                inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.student_record,null);
            studentViewHolder.t_id=convertView.findViewById(R.id.sr_id);
            studentViewHolder.t_name=convertView.findViewById(R.id.sr_names);
            studentViewHolder.t_regno=convertView.findViewById(R.id.sr_reg);
            studentViewHolder.t_gender=convertView.findViewById(R.id.sr_gender);
            studentViewHolder.t_program=convertView.findViewById(R.id.sr_program);
            convertView.setTag(studentViewHolder);
        }
        else {
            studentViewHolder=(StudentViewHolder) convertView.getTag();
        }
        studentViewHolder.t_id.setText(a_id.get(position));
        studentViewHolder.t_name.setText(a_names.get(position));
        studentViewHolder.t_regno.setText(a_regno.get(position));
        studentViewHolder.t_gender.setText(a_gender.get(position));
        studentViewHolder.t_program.setText(a_program.get(position));
        return convertView;
    }

}
class StudentViewHolder{
    TextView t_id,t_name,t_regno,t_gender,t_program;
}
