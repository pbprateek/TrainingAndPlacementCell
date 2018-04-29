package com.aikya.rnsit.trainingandplacementcell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentsDbHelper;

public class MyProfile extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private TextView mobile;
    private TextView branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.contact);
        branch=findViewById(R.id.branch);

        Student student=new StudentsDbHelper(this).getStudent(UserPrefrenceManager.getUserEmail(this));
        name.setText("Name: "+student.getName());
        email.setText("Email: "+student.getEmail());
        mobile.setText("Mobile: "+student.getContact());
        branch.setText("Branch: "+student.getBranch());
    }
}
