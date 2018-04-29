package com.aikya.rnsit.trainingandplacementcell.Adapters;

/*
 *
 * Copyright 2017 Rozdoum
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aikya.rnsit.trainingandplacementcell.Adapters.ViewHolders.StudentsViewHolder;
import com.aikya.rnsit.trainingandplacementcell.Data.Student;
import com.aikya.rnsit.trainingandplacementcell.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 10.05.17.
 */

public class AllStudentsAdapter extends RecyclerView.Adapter<StudentsViewHolder> {
    private List<Student> list = new ArrayList<>();

    @Override
    public StudentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list_view, parent, false);
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentsViewHolder holder, int position) {
        holder.bindData(getItemByPosition(position));
    }

    public Student getItemByPosition(int position) {
        return list.get(position);
    }

    public void setList(List<Student> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

