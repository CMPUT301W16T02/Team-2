package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button logInButton;
    Button signUpButton;
    EditText usernameText;


    //final ConnectionCheck connection = new ConnectionCheck();

    public Button getSignUpButton(){
        return signUpButton;
    }
    public Button getLogInButton(){
        return signUpButton;
    }
    public EditText getEditText(){
        return usernameText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInButton = (Button)findViewById(R.id.logInButton);
        signUpButton = (Button)findViewById(R.id.signUpButton);
        usernameText = (EditText)findViewById(R.id.usernameText);

        logInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                usernameText = (EditText)findViewById(R.id.usernameText);
                final String username = usernameText.getText().toString();

                try{
                    SearchThread thread = new SearchThread(username);
                    thread.start();
                }
                catch(BlankFieldException e){
                    Toast.makeText(getApplicationContext(), "Not Valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //TODO: Integrate connection prompt
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent setIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(setIntent);
            }
        });

    }

    class SearchThread extends Thread{
        private String search;

        public SearchThread(String search) throws BlankFieldException{
            if(search.equals("")) throw new BlankFieldException();
            else this.search = search;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /* taken from:
     * http://stackoverflow.com/questions/16896513/avoiding-call-to-oncreate-of-background-activity-by-pressing-back-of-finish-from */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
