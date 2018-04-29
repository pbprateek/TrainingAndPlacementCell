package com.aikya.rnsit.trainingandplacementcell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentsDbHelper;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private TextView mobile;
    private TextView branch;
    private TextView tenper;
    private TextView tweper;
    private TextView btper;
    private TextView yearten;
    private TextView yeartwe;
    private TextView yearbtch;

    CircleImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.contact);
        branch=findViewById(R.id.branch);
        tenper=findViewById(R.id.tenthper);
        tweper=findViewById(R.id.twelper);
        btper=findViewById(R.id.btechper);
        yearten=findViewById(R.id.yearten);
        yeartwe=findViewById(R.id.yeartwelve);
        yearbtch=findViewById(R.id.yearbtech);
        profilePicture=findViewById(R.id.contactImage);

        Student student=new StudentsDbHelper(this).getStudent(UserPrefrenceManager.getUserEmail(this));
        name.setText("Name: "+student.getName());
        email.setText("Email: "+student.getEmail());
        mobile.setText("Mobile: "+student.getContact());
        branch.setText("Branch: "+student.getBranch());
        if(!student.getMarks10().equals("0"))
            tenper.setText("10th: "+student.getMarks10());
        else
            tweper.setText("10th: "+"NOT UPDATED");
        if(!student.getMarks12().equals("0"))
            tweper.setText("12th: "+student.getMarks12());
        else
            tweper.setText("12th: "+"NOT UPDATED");
        if(!student.getMarksbtech().equals("0"))
            btper.setText("B.Tech: "+student.getMarksbtech());
        else
            btper.setText("B.Tech: "+"NOT UPDATED");

        if(!student.getYear10().equals("0"))
            yearten.setText("10th: "+student.getYear10());
        else
            yearten.setText("10th: "+"NOT UPDATED");
        if(!student.getYear12().equals("0"))
            yeartwe.setText("12th: "+student.getYear12());
        else
            yeartwe.setText("12th: "+"NOT UPDATED");
        if(!student.getYearbtech().equals("0"))
            yearbtch.setText("B.Tech: "+student.getYearbtech());
        else
            yearbtch.setText("B.Tech: "+"NOT UPDATED");




        if(!student.getImage().equals("0")){
            Glide.with(MyProfile.this)
                    .load(student.getImage())
                    .into(profilePicture);

        }
    }
}
