package com.aikya.rnsit.trainingandplacementcell.Data;

import android.provider.BaseColumns;

public class StudentInfo {
    public static final class StudentsEntry  implements BaseColumns{;
        public static final String TABLE_NAME="students";

        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String CONTACT = "contact";
        public static final String BRANCH = "branch";
        public static final String MARKS10 = "marks10";
        public static final String MARKS12 = "marks12";
        public static final String MARKSBTECH = "marksbtech";
        public static final String YEAR10 = "year10";
        public static final String YEAR12 = "year12";
        public static final String YEARBTECH = "yearbtech";
        public static final String PLACED="placed";
        public static final String IMAGE="image";
        public static final String _ID = "_id";

    }
}
