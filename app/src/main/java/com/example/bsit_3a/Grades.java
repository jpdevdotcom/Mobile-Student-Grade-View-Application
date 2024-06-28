package com.example.bsit_3a;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Grades extends AppCompatActivity {

    //String[] schoolyears = new String[]{"2020-2021 1st Semester", "2020-2021 2nd Semester"};

    List<SchoolYear> schoolYearList = new ArrayList<SchoolYear>();

    List<Grade> gradeList = new ArrayList<>();
    Spinner spinnerSchoolYear;

    RecyclerView gradeRecyclerView;



    DataAccess da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        this.setTitle("GRADES");

        spinnerSchoolYear = findViewById(R.id.spinner);
        gradeRecyclerView = findViewById(R.id.gradesrecycler);

        da = new DataAccess(this);

        schoolYearList = da.GetSchoolYears();

        //populateSchoolYear();

        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, schoolyears);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        GradeRecyclerViewAdapter gradeRecyclerViewAdapter = new GradeRecyclerViewAdapter();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setItemPrefetchEnabled(true);
        gradeRecyclerView.setLayoutManager(mLayoutManager);
        gradeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        gradeRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        gradeRecyclerViewAdapter.grades = gradeList;
        gradeRecyclerView.setAdapter(gradeRecyclerViewAdapter);

        SchoolYearSpinnerAdapter adapter2 = new SchoolYearSpinnerAdapter(this, android.R.layout.simple_spinner_dropdown_item, schoolYearList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSchoolYear.setAdapter(adapter2);

        spinnerSchoolYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SchoolYear sy = schoolYearList.get(position);

                gradeList = da.getGrades(sy.SYId);
                gradeRecyclerViewAdapter.grades = gradeList;
//                rvGrades.setAdapter(ra);

                gradeRecyclerViewAdapter.notifyDataSetChanged();

                String str = schoolYearList.get((int)id).SchoolYearStart + "-" + schoolYearList.get((int)id).SchoolYearEnd + "" + schoolYearList.get((int)id).SchoolYearSemester;
                Toast.makeText(getApplicationContext(), "You selected" + str,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

   // private void populateSchoolYear()
//    {
//        SchoolYear sy;
//
//        sy = new SchoolYear();
//        sy.SchoolYearStart = "2020";
//        sy.SchoolYearEnd = "2021";
//        sy.SchoolYearSemester = "1st Semester";
//        schoolYearList.add(sy);
//
//        sy = new SchoolYear();
//        sy.SchoolYearStart = "2020";
//        sy.SchoolYearEnd = "2021";
//        sy.SchoolYearSemester = "2nd Semester";
//        schoolYearList.add(sy);
//
//        sy = new SchoolYear();
//        sy.SchoolYearStart = "2021";
//        sy.SchoolYearEnd = "2022";
//        sy.SchoolYearSemester = "1st Semester";
//        schoolYearList.add(sy);
//
//        sy = new SchoolYear();
//        sy.SchoolYearStart = "2021";
//        sy.SchoolYearEnd = "2022";
//        sy.SchoolYearSemester = "2nd Semester";
//        schoolYearList.add(sy);
//    }
}