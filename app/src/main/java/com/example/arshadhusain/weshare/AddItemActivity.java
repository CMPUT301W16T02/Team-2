package com.example.arshadhusain.weshare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * <p>AddItemActivity allows user to add an item. Passes intent of user and
 * provides user interface for input</p>
 *
 * @Author: Chris
 * @Version: 1.0
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private String owner = "Username";
    public Context context1;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ImageButton pictureButton;
    private Bitmap thumbnail;

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

        //context1.getApplicationContext();

        context1 = NavigationMainActivity.getContext();

        Intent intent = getIntent();

        if(intent.hasExtra("activeUser")) {
            owner = intent.getStringExtra("activeUser");
        }

        name = (EditText) findViewById(R.id.itemName);
        description = (EditText) findViewById(R.id.itemDesc);

        Button addItem = (Button)findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                addItem(v, owner);
            }
        });

        final Button cancel = (Button)findViewById(R.id.cancelButton);

    cancel.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            cancel(v);
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

        Item newItem = new Item(itemName, itemDesc, owner);
        newItem.addThumbnail(thumbnail);

        /*NavigationMainActivity.allItems.add(newItem);
        NavigationMainActivity.saveInFile(context1);
        System.out.printf("%d\n", NavigationMainActivity.allItems.size());*/
        //NavigationMainActivity.addAndSaveToItems(newItem);


        ElasticSearchAppController.AddItemTask addItemTask = new ElasticSearchAppController.AddItemTask();
        addItemTask.execute(newItem);

        pictureButton.setImageResource(android.R.color.transparent);
        thumbnail = null;
        setResult(RESULT_OK);
        finish();

        if(itemName.isEmpty() && itemDesc.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your information into each field", Toast.LENGTH_LONG).show();


        } else if(itemName.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter an item name", Toast.LENGTH_LONG).show();

        } else if (itemDesc.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter an item description", Toast.LENGTH_LONG).show();

        } else {

            addItemTask = new ElasticSearchAppController.AddItemTask();
            addItemTask.execute(newItem);
            setResult(RESULT_OK);
            finish();
        }

    }

    /**
     * User stops inputing item
     * @param view
     */
    public void cancel(View view) {

        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            thumbnail = (Bitmap) extras.get("data");
            pictureButton.setImageBitmap(thumbnail);
        }
    }
}