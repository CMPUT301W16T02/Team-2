package com.example.arshadhusain.weshare;

import android.app.Application;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Overseer on 2016-03-14.
 */
public class CreateProfileActivityTest extends ActivityInstrumentationTestCase2 {
    boolean valid;
    private Context context;
    private EditText username;
    private EditText email;
    private EditText city;
    private ArrayList<Account> Accounts = new ArrayList<Account>();
    ArrayAdapter<Account> adapter;

    public CreateProfileActivityTest(){
        super(CreateProfileActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

}