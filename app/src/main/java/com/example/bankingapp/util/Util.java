package com.example.bankingapp.util;

import androidx.fragment.app.Fragment;

import com.example.bankingapp.data.DatabaseHandler;
import com.example.bankingapp.model.FromTo;

public class Util {
    public static FromTo sFromTo = new FromTo();
    public static Fragment homeFragment;
    public static DatabaseHandler db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "user_db";
    public static final String TABLE_NAME_USER = "user_table";
    public static final String TABLE_NAME_HISTORY = "history_table";

    //User Table
    public static final String USER_ID = "user_id";
    public static final String USER_PHONE_NUMBER = "user_phone_number";
    public static final String USER_NAME = "user_name";
    public static final String USER_BALANCE = "user_balance";
    public static final String USER_EMAIL = "user_email";

    //History Table
    public static final String HISTORY_ID = "history_id";
    public static final String HISTORY_DATE = "history_date";
    public static final String HISTORY_FROM = "history_from";
    public static final String HISTORY_TO = "history_to";
    public static final String HISTORY_AMOUNT = "history_amount";



}
