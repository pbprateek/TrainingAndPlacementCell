package com.aikya.rnsit.trainingandplacementcell;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentInfo;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentsDbHelper;
import com.bumptech.glide.Glide;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AcademicProfile extends AppCompatActivity {
    @BindView(R.id.tenthper)
    EditText tenmarks;
    @BindView(R.id.twelper)
    EditText twelvemarks;
    @BindView(R.id.btechper)
    EditText btechmarks;
    @BindView(R.id.yearten)
    EditText passten;
    @BindView(R.id.yeartwelve)
    EditText passtwelve;
    @BindView(R.id.yearbtech)
    EditText passbtech;
    @BindView(R.id.btn_submit)
    Button submitButton;
    @BindView(R.id.checkbox_placed)
    CheckBox placedCheck;
    @BindView(R.id.contactImage)
    CircleImageView profilePicture;


    private StudentsDbHelper dbHelper;
    private boolean placed = false;
    private static final int REQUEST_CODE = 1;
    private String mSelectedImagePath=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_profile);
        ButterKnife.bind(this);
        dbHelper = new StudentsDbHelper(this);
        Student student = dbHelper.getStudent(UserPrefrenceManager.getUserEmail(this));
        tenmarks.setText(student.getMarks10());
        twelvemarks.setText(student.getMarks12());
        btechmarks.setText(student.getMarksbtech());
        passten.setText(student.getYear10());
        passtwelve.setText(student.getYear12());
        passbtech.setText(student.getYearbtech());
        if(!student.getImage().equals("0")){
            Glide.with(AcademicProfile.this)
                    .load(student.getImage())
                    .into(profilePicture);

        }
        if (student.getPlaced() == 1)
            placed = true;
        placedCheck.setChecked(placed);
        submitButton.setOnClickListener(v -> submit());


        ImageView editImage = (ImageView) findViewById(R.id.ivCamera);
        editImage.setOnClickListener(v -> {
            for( int i = 0; i < Init.PERMISSIONS.length; i++){
                String[] permission = {Init.PERMISSIONS[i]};
                if(checkPermission(permission)){
                    if(i == Init.PERMISSIONS.length - 1){
                        selectImage();
                    }
                }else{
                    verifyPermissions(permission);
                }
            }

        });

    }


    private void selectImage() {
        final CharSequence[] items = { "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AcademicProfile.this);
        builder.setTitle("Add Photo!");

        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Choose from Library")) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Init.PICKFILE_REQUEST_CODE);


            } else  {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Init.PICKFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();
            File file = new File(selectedImageUri.toString());
            String imagePath=file.getPath();
            if( !imagePath.equals("")){
                imagePath = imagePath.replace(":/", "://");
                mSelectedImagePath = imagePath;
                Glide.with(AcademicProfile.this)
                        .load(imagePath)
                        .into(profilePicture);
            }

        }
    }

    public void verifyPermissions(String[] permissions) {
        Log.d("ABC", "verifyPermissions: asking user for permissions.");
        ActivityCompat.requestPermissions(
                AcademicProfile.this,
                permissions,
                REQUEST_CODE
        );
    }

    /**
     * Checks to see if permission was granted for the passed parameters
     * ONLY ONE PERMISSION MAYT BE CHECKED AT A TIME
     *
     * @param permission
     * @return
     */
    public boolean checkPermission(String[] permission) {

        int permissionRequest = ActivityCompat.checkSelfPermission(
                AcademicProfile.this,
                permission[0]);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("ABC", "onRequestPermissionsResult: User has allowed permission to access: " + permissions[i]);
                    } else {
                        break;
                    }
                }
                break;
        }
    }


    private void submit() {

        if (!validate()) {
            onSignupFailed();
            return;
        }


        hideKeyBoard();
        submitButton.setEnabled(false);

        String tenthmarksinput = tenmarks.getText().toString();
        String twelvemarksinput = twelvemarks.getText().toString();
        String btechmarksinput = btechmarks.getText().toString();
        String passtenthinput = passten.getText().toString();
        String passtwelveinput = passtwelve.getText().toString();
        String passbtechinput = passbtech.getText().toString();
        int isplaced;
        if (placedCheck.isChecked())
            isplaced = 1;
        else
            isplaced = 0;


        ContentValues values = new ContentValues();
        values.put(StudentInfo.StudentsEntry.MARKS10, tenthmarksinput);
        values.put(StudentInfo.StudentsEntry.MARKS12, twelvemarksinput);
        values.put(StudentInfo.StudentsEntry.MARKSBTECH, btechmarksinput);
        values.put(StudentInfo.StudentsEntry.YEAR10, passtenthinput);
        values.put(StudentInfo.StudentsEntry.YEAR12, passtwelveinput);
        values.put(StudentInfo.StudentsEntry.YEARBTECH, passbtechinput);
        values.put(StudentInfo.StudentsEntry.PLACED, isplaced);
        if(mSelectedImagePath != null)
            values.put(StudentInfo.StudentsEntry.IMAGE,mSelectedImagePath);

        dbHelper.updateStudent(UserPrefrenceManager.getUserEmail(this), values);
        super.onBackPressed();

    }

    public void onSignupFailed() {
        hideKeyBoard();
        submitButton.setEnabled(true);
    }

    private Boolean validate() {
        boolean valid = true;

        String tenthmarksinput = tenmarks.getText().toString();
        String twelvemarksinput = twelvemarks.getText().toString();
        String btechmarksinput = btechmarks.getText().toString();
        String passtenthinput = passten.getText().toString();
        String passtwelveinput = passtwelve.getText().toString();
        String passbtechinput = passbtech.getText().toString();


        if (tenthmarksinput.isEmpty()) {
            tenmarks.setError("Give some input");
            valid = false;
        } else {
            tenmarks.setError(null);
        }

        if (twelvemarksinput.isEmpty()) {
            twelvemarks.setError("Give some input");
            valid = false;
        } else {
            twelvemarks.setError(null);
        }

        if (btechmarksinput.isEmpty()) {
            btechmarks.setError("Give some input");
            valid = false;
        } else {
            btechmarks.setError(null);
        }

        if (passtenthinput.isEmpty()) {
            passten.setError("Give some input");
            valid = false;
        } else {
            passten.setError(null);
        }

        if (passtwelveinput.isEmpty()) {
            passtwelve.setError("Give some input");
            valid = false;
        } else {
            passtwelve.setError(null);
        }

        if (passbtechinput.isEmpty()) {
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
