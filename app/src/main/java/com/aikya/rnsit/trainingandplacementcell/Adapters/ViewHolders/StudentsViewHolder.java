package com.aikya.rnsit.trainingandplacementcell.Adapters.ViewHolders;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.R;

public class StudentsViewHolder extends RecyclerView.ViewHolder {


    private final TextView nameTextView;
    private final TextView branchTextView;
    private final TextView mobileTextView;
    private final TextView placedTextView;
    private Context context;

    public StudentsViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();

        nameTextView =  itemView.findViewById(R.id.nameText);
        branchTextView=itemView.findViewById(R.id.branchText);
        mobileTextView=itemView.findViewById(R.id.mobileText);
        placedTextView=itemView.findViewById(R.id.placedText);
    }

    public void bindData(Student student) {

        nameTextView.setText("Name: "+student.getName());
        branchTextView.setText("Branch: "+student.getBranch());
        mobileTextView.setText("Mobile: "+student.getContact());
        if(student.getPlaced()==0)
            placedTextView.setText("Placed: NO");
        else
            placedTextView.setText("Placed: YES");
    }
}