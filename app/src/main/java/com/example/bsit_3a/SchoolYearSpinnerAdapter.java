package com.example.bsit_3a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class SchoolYearSpinnerAdapter extends ArrayAdapter<SchoolYear> {

    Context context;

    List<SchoolYear> schoolyears;

    public SchoolYearSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<SchoolYear> objects)
    {
        super(context, resource, objects);
        schoolyears = objects;
        this.context = context;
    }

    @Override
    public int getCount() {return schoolyears.size();}

    @Override
    public  SchoolYear getItem(int i)  { return  schoolyears.get(i); }

    @Override
    public  long getItemId(int i)  { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view ==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.school_year_spinner_item, viewGroup, false);
        }
        SchoolYear schoolyear = schoolyears.get(i);

        TextView TxtchoolYearStart = view.findViewById(R.id.txtSchoolYearStart);
        TextView TxtchoolYearEnd = view.findViewById(R.id.txtSchoolYearEnd);
        TextView TxtchoolYearSemester = view.findViewById(R.id.txtSchoolYearSemester);

        TxtchoolYearStart.setText(schoolyear.SchoolYearStart);
        TxtchoolYearEnd.setText(schoolyear.SchoolYearEnd);
        TxtchoolYearSemester.setText(schoolyear.SchoolYearSemester);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.schoolyear_spinner_item, parent, false);
        }

//        SchoolYear schoolyear = schoolyears.get(position);
//
//        TextView TxtchoolYearStart = convertView.findViewById(R.id.txtSchoolYearStart);
//        TextView TxtchoolYearEnd = convertView.findViewById(R.id.txtSchoolYearEnd);
//        TextView TxtchoolYearSemester = convertView.findViewById(R.id.txtSchoolYearSemester);
//
//        TxtchoolYearStart.setText(schoolyear.SchoolYearStart);
//        TxtchoolYearEnd.setText(schoolyear.SchoolYearEnd);
//        TxtchoolYearSemester.setText(schoolyear.SchoolYearSemester);

        return  getView(position, convertView, parent);
    }
}
