package com.aikya.rnsit.trainingandplacementcell;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentInfo;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentsDbHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcademicProfile extends AppCompatActivity {
    @BindView(R.id.tenthper) EditText tenmarks;
    @BindView(R.id.twelper) EditText twelvemarks;
    @BindView(R.id.btechper) EditText btechmarks;
    @BindView(R.id.yearten) EditText passten;
    @BindView(R.id.yeartwelve) EditText passtwelve;
    @BindView(R.id.yearbtech) EditText passbtech;
    @BindView(R.id.btn_submit) Button submitButton;
    @BindView(R.id.checkbox_placed) CheckBox placedCheck;

    private StudentsDbHelper dbHelper;
    private boolean placed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_profile);
        ButterKnife.bind(this);
        dbHelper=new StudentsDbHelper(this);
        Student student=dbHelper.getStudent(UserPrefrenceManager.getUserEmail(this));
        tenmarks.setText(student.getMarks10());
        twelvemarks.setText(student.getMarks12());
        btechmarks.setText(student.getMarksbtech());
        passten.setText(student.getYear10());
        passtwelve.setText(student.getYear12());
        passbtech.setText(student.getYearbtech());
        if(student.getPlaced()==1)
            placed=true;
        placedCheck.setChecked(placed);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void submit(){

        if (!validate()) {
            onSignupFailed();
            return;
        }


        hideKeyBoard();
        submitButton.setEnabled(false);

        String tenthmarksinput = tenmarks.getText().toString();
        String twelvemarksinput = twelvemarks.getText().toString();
        String  btechmarksinput= btechmarks.getText().toString();
        String passtenthinput= passten.getText().toString();
        String passtwelveinput = passtwelve.getText().toString();
        String passbtechinput = passbtech.getText().toString();
        int isplaced;
        if(placedCheck.isChecked())
            isplaced=1;
        else
            isplaced=0;


        ContentValues values=new ContentValues();
        values.put(StudentInfo.StudentsEntry.MARKS10,tenthmarksinput);
        values.put(StudentInfo.StudentsEntry.MARKS12,twelvemarksinput);
        values.put(StudentInfo.StudentsEntry.MARKSBTECH,btechmarksinput);
        values.put(StudentInfo.StudentsEntry.YEAR10,passtenthinput);
        values.put(StudentInfo.StudentsEntry.YEAR12,passtwelveinput);
        values.put(StudentInfo.StudentsEntry.YEARBTECH,passbtechinput);
        values.put(StudentInfo.StudentsEntry.PLACED,isplaced);

        dbHelper.updateStudent(UserPrefrenceManager.getUserEmail(this),values);
        super.onBackPressed();

    }
    public void onSignupFailed() {
        hideKeyBoard();
        submitButton.setEnabled(true);
    }
    private Boolean validate(){
        boolean valid = true;

        String tenthmarksinput = tenmarks.getText().toString();
        String twelvemarksinput = twelvemarks.getText().toString();
        String  btechmarksinput= btechmarks.getText().toString();
        String passtenthinput= passten.getText().toString();
        String passtwelveinput = passtwelve.getText().toString();
        String passbtechinput = passbtech.getText().toString();


        if (tenthmarksinput.isEmpty() ) {
            tenmarks.setError("Give some input");
            valid = false;
        } else {
            tenmarks.setError(null);
        }

        if (twelvemarksinput.isEmpty() ) {
            twelvemarks.setError("Give some input");
            valid = false;
        } else {
            twelvemarks.setError(null);
        }

        if (btechmarksinput.isEmpty() ) {
            btechmarks.setError("Give some input");
            valid = false;
        } else {
            btechmarks.setError(null);
        }

        if (passtenthinput.isEmpty() ) {
            passten.setError("Give some input");
            valid = false;
        } else {
            passten.setError(null);
        }

        if (passtwelveinput.isEmpty() ) {
            passtwelve.setError("Give some input");
            valid = false;
        } else {
            passtwelve.setError(null);
        }

        if (passbtechinput.isEmpty() ) {
            passbtech.setError("Give some input");
            valid = false;
        } else {
            passbtech.setError(null);
        }


        return valid;

    }


    private void hideKeyBoard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
