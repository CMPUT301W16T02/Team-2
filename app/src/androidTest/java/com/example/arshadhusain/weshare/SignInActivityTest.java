package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.EditText;

/**
 * Created by hasan on 2016-03-12.
 */
public class SignInActivityTest extends ActivityInstrumentationTestCase2 {
    Instrumentation instrumentation;
    Activity activity;
    EditText loginUsername;

    public SignInActivityTest(){
        super(SignInActivity.class);
    }

    protected void setUp() throws Exception{
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();
        loginUsername = (EditText)activity.findViewById(R.id.loginUsername);
    }

    private void login(String username){
        assertNotNull(activity.findViewById(R.id.loginUsername));
        loginUsername.setText(username);

        activity.findViewById(R.id.loginUsername).performClick();
    }

    @UiThreadTest
    public void testLogin(){
        login("HasanBadran");
        assertEquals("HasanBadran", Account.getUsername());
    }
}
