package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * <p>AddItemActivity allows user to add an item. Passes intent of user and
 * provides user interface for input</p>
 *
 * <p>Now incorporated camera  and elastic search functionality from lonelyTwitter</p>
 *
 * @Author: Christopher, Philip, Arshad
 * @Version: 2.5
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private String myUsername;
    private ImageButton pictureButton;
    private Bitmap thumbnail;
    final static int CHANGE_MADE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        pictureButton = (ImageButton) findViewById(R.id.pictureButton);
        pictureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

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
     * addItem method creates new item and add it to elastic search server
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
            Toast.makeText(getApplicationContext(), "Please enter an item description", Toast.LENGTH_LONG).show();
        } else {
            Item newItem = new Item(itemName, itemDesc, owner);
            newItem.addThumbnail(thumbnail);

            ElasticSearchAppController.AddItemTask addItemTask = new ElasticSearchAppController.AddItemTask();
            addItemTask.execute(newItem);

            pictureButton.setImageResource(android.R.color.transparent);
            thumbnail = null;

            setResult(RESULT_OK);
            finish();
        }
    }

    /**
     * Camera functionality
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            thumbnail = (Bitmap) extras.get("data");
            pictureButton.setImageBitmap(thumbnail);
        }
    }
}