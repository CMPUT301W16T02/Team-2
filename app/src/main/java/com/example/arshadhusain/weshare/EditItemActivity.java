package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private ListView bidsList;

    private int itemPos;
    private Item itemToEdit;

    public Context context1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        context1 = NavigationMainActivity.getContext();

        Intent intent = getIntent();

        if(intent.hasExtra("itemPos")) {
            itemPos = intent.getIntExtra("itemPos", itemPos);
        }

        itemToEdit = NavigationMainActivity.allItems.get(itemPos);

        name = (EditText) findViewById(R.id.itemName);
        description = (EditText) findViewById(R.id.itemDesc);
        bidsList = (ListView) findViewById(R.id.bidsList);

        name.setText(itemToEdit.getName());
        description.setText(itemToEdit.getDescription());



        Button saveEdit = (Button) findViewById(R.id.saveButton);

        saveEdit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editItem(v);
            }
        });

        Button delete = (Button)findViewById(R.id.deleteButton);

        delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                deleteItem(v);
            }
        });

        Button cancel = (Button)findViewById(R.id.cancelButton);

        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                cancel(v);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Bid> bids = itemToEdit.getListBid();
        ArrayAdapter<Bid> adapter = new ArrayAdapter<Bid>(this,
                R.layout.list_item, bids);
        bidsList.setAdapter(adapter);
    }

    public void editItem(View view){

        String newName = name.getText().toString();
        String newDesc = description.getText().toString();

        itemToEdit.setName(newName);
        itemToEdit.setDescription(newDesc);

        NavigationMainActivity.saveInFile(context1);

        setResult(RESULT_OK);
        finish();
    }

    public void deleteItem(View view){
        NavigationMainActivity.allItems.remove(itemToEdit);

        NavigationMainActivity.saveInFile(context1);
        System.out.printf("%d\n", NavigationMainActivity.allItems.size());

        setResult(RESULT_OK);
        finish();
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void getItemsBids() {
        
    }
}