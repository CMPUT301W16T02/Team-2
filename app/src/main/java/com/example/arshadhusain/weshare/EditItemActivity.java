package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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
    private String activeUser;

    private int selectedItemPos;

    public Context context1;
    final Context context = this;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        context1 = NavigationMainActivity.getContext();

        Intent intent = getIntent();

        if(intent.hasExtra("itemPos")) {
            itemPos = intent.getIntExtra("itemPos", itemPos);
        }
        /*
        if(intent.hasExtra("activeUser")) {
            activeUser = intent.getStringExtra("activeUser");
        }*/

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

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedItemPos = position;
        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if(intent.hasExtra("activeUser")) {
            activeUser = intent.getStringExtra("activeUser");
        }

        ArrayList<Bid> bids = getItemsBids();
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

    public ArrayList<Bid> getItemsBids() {
        ArrayList<Bid> itemBids = new ArrayList<Bid>();
        for(int i=0; i<NavigationMainActivity.allBids.size(); i++){
            String itemOwner = NavigationMainActivity.allBids.get(i).getItemOwner();
            if(itemOwner.equals(activeUser)){
                itemBids.add(NavigationMainActivity.allBids.get(i));
            }
        }
        return itemBids;
    }
}