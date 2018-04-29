package com.aikya.rnsit.trainingandplacementcell;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPrefrenceManager {

    private static SharedPreferences mSharedPreferences;
    private static final String PREF_NAME = "PLACEMENT_CELL";


    private static String USER_EMAIL="email";
    private static String LOGIN_TYPE="login_type";

    private static void init(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void login(Context mContext,
                             String userEmail,
                             String loginType){
        if(mSharedPreferences==null){
            init(mContext);
        }
        SharedPreferences.Editor mShEditor=mSharedPreferences.edit();

        mShEditor.putString(USER_EMAIL,userEmail);
        mShEditor.putString(LOGIN_TYPE,loginType);
        mShEditor.commit();
    }


    public static void logout(Context mContext){
        if(mSharedPreferences==null){
            init(mContext);
        }
        SharedPreferences.Editor mSEditor=mSharedPreferences.edit();
        mSEditor.clear();
        mSEditor.commit();
    }


    public static String getUserEmail(Context mContext){
        String mUserEmail="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        mUserEmail=mSharedPreferences.getString(USER_EMAIL,"");
        return mUserEmail;
    }

    public static String getLoginType(Context mContext){
        String loginType="";
        if(mSharedPreferences==null){
            init(mContext);
        }
        loginType=mSharedPreferences.getString(LOGIN_TYPE,"");
        return loginType;
    }

}
