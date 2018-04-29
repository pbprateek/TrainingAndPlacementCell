package com.aikya.rnsit.trainingandplacementcell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.TextView;

import com.aikya.rnsit.trainingandplacementcell.Adapters.AllStudentsAdapter;
import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.Data.StudentsDbHelper;

import java.util.ArrayList;
import java.util.List;

public class PlacedStudentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AllStudentsAdapter allStudentsAdapter;
    private StudentsDbHelper dbHelper;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_students);
        dbHelper=new StudentsDbHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        textView=findViewById(R.id.emptylist);
        allStudentsAdapter = new AllStudentsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        recyclerView.setAdapter(allStudentsAdapter);
        List<Student> students=dbHelper.getAllStudents();
        List<Student> placedStudent=new ArrayList<>();
        for(Student stud:students){
            if(stud.getPlaced()==1)
                placedStudent.add(stud);
        }
        if(placedStudent.size()==0)
            textView.setVisibility(View.VISIBLE);
        allStudentsAdapter.setList(placedStudent);
    }
}
