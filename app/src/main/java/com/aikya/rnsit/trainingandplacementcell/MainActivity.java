package com.aikya.rnsit.trainingandplacementcell;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aikya.rnsit.trainingandplacementcell.MyProfile;
import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentInfo;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentsDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StudentsDbHelper studentsDbHelper;
    private Button logOut;
    private Button myProfile;
    private Button acadProfile;
    private Button allStud;
    private Button placedStud;
    private Button upcomingCompanies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logOut=findViewById(R.id.log_out);
        myProfile=findViewById(R.id.profile);
        acadProfile=findViewById(R.id.acad_profile);
        allStud=findViewById(R.id.all_stud);
        placedStud=findViewById(R.id.placed_stud);
        upcomingCompanies=findViewById(R.id.upcomingcompanies);
        upcomingCompanies.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, UpcomingCompaniesActivity.class);
            startActivity(intent);

        });
        myProfile.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, MyProfile.class);
            startActivity(intent);
        });
        acadProfile.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, AcademicProfile.class);
            startActivity(intent);
        });
        allStud.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, AllStudentsActivity.class);
            startActivity(intent);

        });

        placedStud.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, PlacedStudentsActivity.class);
            startActivity(intent);

        });


        logOut.setOnClickListener(v -> logOut());


    }

    private void logOut() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setMessage("Are you sure, you want to logout?");
        mBuilder.setCancelable(true);
        mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                UserPrefrenceManager.logout(MainActivity.this);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.create();
        mBuilder.show();
    }
}
