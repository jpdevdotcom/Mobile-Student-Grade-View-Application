package com.example.bsit_3a;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GradeRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView txtSubjectCode, txtSubjectDescription, txtGrade;

    public GradeRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        txtSubjectCode = itemView.findViewById(R.id.txtsubjectcode);
        txtSubjectDescription = itemView.findViewById(R.id.txtsubjectdescription);
        txtGrade = itemView.findViewById(R.id.txtgrade);
    }
}

