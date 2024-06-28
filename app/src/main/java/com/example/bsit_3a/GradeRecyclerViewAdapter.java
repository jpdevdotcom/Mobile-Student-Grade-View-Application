package com.example.bsit_3a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeRecyclerViewAdapter extends RecyclerView.Adapter<GradeRecyclerViewHolder> {

    public List<Grade> grades;

    @NonNull
    @Override
    public GradeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_item, parent, false);
        return new GradeRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeRecyclerViewHolder holder, int position){

        Grade gd = grades.get(position);

        holder.txtSubjectCode.setText(gd.SubjectCode);
        holder.txtSubjectDescription.setText(gd.SubjectDescription);
        holder.txtGrade.setText(gd.Grade);
    }

    @Override
    public int getItemCount() {

        return grades.size();
    }
}
