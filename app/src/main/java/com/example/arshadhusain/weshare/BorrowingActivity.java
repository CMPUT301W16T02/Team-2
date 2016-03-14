package com.example.arshadhusain.weshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
* @deprecated
 * */
public class BorrowingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        //this.onCreateListeners();

    }

    public void onCreateSetup() {
        setContentView(R.layout.borrowing_activity);

        //get a list of items that you are borrowing
    }


}
