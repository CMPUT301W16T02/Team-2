package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <p>AddItemActivity allows user to add an item. Passes intent of user and
 * provides user interface for input</p>
 *
 * @Author: Christopher
 * @Version: 2.0
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private String myUsername;
    final static int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent();

        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        name = (EditText) findViewById(R.id.itemNameField);
        description = (EditText) findViewById(R.id.itemDescField);

        Button addItem = (Button)findViewById(R.id.addItemButton);

        addItem.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                addItem(v, myUsername);
            }
        });
    }

    /**
     * addItem method creates new item and appends to allItems
     *
     * @see MainItemListActivity
     * @param view
     * @param owner
     */
    public void addItem(View view, String owner){

        String itemName = name.getText().toString();
        String itemDesc = description.getText().toString();

        if (itemName.isEmpty() && itemDesc.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your information into each field", Toast.LENGTH_LONG).show();
        } else if (itemName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter an item name", Toast.LENGTH_LONG).show();
        } else if (itemDesc.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter an item desceription", Toast.LENGTH_LONG).show();
        } else {
            Item newItem = new Item(itemName, itemDesc, owner);

            ElasticSearchAppController.AddItemTask addItemTask = new ElasticSearchAppController.AddItemTask();
            addItemTask.execute(newItem);
            setResult(RESULT_OK);
            finish();
        }
    }
}