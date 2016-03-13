package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

/**
 * Created by hasan on 2016-03-12.
 */
public class SignUpActivityTest extends ActivityInstrumentationTestCase2 {
    Instrumentation instrumentation;
    Activity activity;
    EditText signupUsername;
    EditText signupCity;
    EditText signupEmail;

    public SignUpActivityTest() {
        super(SignUpActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();
        signupUsername = (EditText) activity.findViewById(R.id.signupUsername);
        signupCity = (EditText) activity.findViewById(R.id.signupCity);
        signupEmail = (EditText) activity.findViewById(R.id.signupEmail);
    }

    private void register(String username,String password) {
        assertNotNull(activity.findViewById(R.id.signupSignUp));
        signupUsername.setText(username);
        signupCity.setText(city);
        signupEmail.setText(email);

        activity.findViewById(R.id.signupSignUp).performClick();
    }

    private boolean findByUsername(String username) {
        PlayerController.FindByUserName findByUsername = player.new FindByUserName();
        findByUsername.execute("joe");
        try {
            boolean userExists = findByUsername.get();
            Log.w("word", "value of userExists");
            Log.w("word", String.valueOf(userExists));
            return userExists;
            /*if (userExists) {
                // delete old user
                client.execute(new Delete.Builder(joe).index("users").type("user").build());
            }*/

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    @UiThreadTest
    public void testRegister(){
        Instrumentation.ActivityMonitor login = getInstrumentation().addMonitor(SignInActivity.class.getName(),null,true);
        Instrumentation.ActivityMonitor main = getInstrumentation().addMonitor(MainActivity.class.getName(),null,true);
        PlayerController player = new PlayerController();

        // assert user doesn't already login successfully
        try{
            boolean canLogin = player.new Authenticate().execute("Jeff", "word").get();
            if (canLogin) {
                // delete user Jeff
            }
            assertFalse(canLogin);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        register("Jeff", "word");

        // assert intent sent to login
        login.waitForActivityWithTimeout(100);
        assertEquals(1, login.getHits());

        // just waste time to let register finish because for some reason it is delayed
        // doens't work without this so its probably a pretty poor test
        main.waitForActivityWithTimeout(1000);
        assertEquals(0, main.getHits());

        // assert user can login successfully
        try {
            assertTrue(player.new Authenticate().execute("Jeff", "word").get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
