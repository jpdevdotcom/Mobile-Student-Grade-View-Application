package com.example.bsit_3a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnViewGrades;

    Button btnViewSubjects;

    Button btnViewEvaluation;

    Button btnUpdate;

    ProgressBar progressBar;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewGrades = findViewById(R.id.grades_button);
        btnViewSubjects = findViewById(R.id.subjects_button);
        btnViewEvaluation = findViewById(R.id.evaluation_button);
        btnUpdate = findViewById(R.id.button_update);

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        btnViewGrades.setOnClickListener(this);
        btnViewSubjects.setOnClickListener(this);
        btnViewEvaluation.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public  void  onClick(View v) {
        if (v.getId() == R.id.grades_button)
        {
            Intent intent;
            intent = new Intent(this, Grades.class);

            startActivity(intent);
        }

        if (v.getId() == R.id.subjects_button)
        {
            Intent intent;
            intent = new Intent(this, Subjects.class);

            startActivity(intent);
        }

        if (v.getId() == R.id.evaluation_button)
        {
            Intent intent;
            intent = new Intent(this, Evaluation.class);
            //intent.putExtra("DataFrom", DataFrom);
            //intent.putExtra("DataTo", DataTo);
            startActivity(intent);
        }

       if (v.getId() == R.id.button_update)
     {
            String studentID = "200832";
           String birthdate = "01232020";

           WebApi getStudentRecords = new WebApi(this, studentID, birthdate, progressBar);
           getStudentRecords.execute();
       }
    }

    public void clickSubjects(View view) {
        Intent subjects = new Intent(MainActivity.this, Subjects.class);
        startActivity(subjects);
    }

    public void clickGrades(View view) {
    }

    public void clickEvaluation(View view) {
        Intent evaluation = new Intent(MainActivity.this, Evaluation.class);
        startActivity(evaluation);
    }
}
//  public class MainActivity extends AppCompatActivity{
//      @Override
//      protected  void onCreate(Bundle savedInstances){
//          super.onCreate(savedInstances);
//          setContentView(R.layout.activity_main);
//
//          Button GradesBtn = findViewById(R.id.grades_button);
//          GradesBtn.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  Intent intent = new Intent (MainActivity.this, Grades.class);
//                  startActivity(intent);
//              }
//          });
//
//          Button SubBtn = findViewById(R.id.subjects_button);
//          SubBtn.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  Intent intent = new Intent(MainActivity.this, Subjects.class);
//                  startActivity(intent);
//              }
//          });
//
//          Button EvalBtn  = findViewById(R.id.evaluation_button);
//          EvalBtn.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  Intent intent = new Intent(MainActivity.this, Evaluation.class);
//                  startActivity(intent);
//              }
//          });
//      }
//  }