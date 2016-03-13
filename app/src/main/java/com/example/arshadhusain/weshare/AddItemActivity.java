package com.example.arshadhusain.weshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hasan on 2016-03-12.
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private String owner = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

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

    public void addItem(View view, String owner){

        String itemName = name.getText().toString();
        String itemDesc = description.getText().toString();

        Item newItem = new Item(itemName, itemDesc, owner);

        MainItemListActivity.allItems.add(newItem);

        setResult(RESULT_OK);
        finish();
    }

    public void cancel(View view) {

        setResult(RESULT_CANCELED);
        finish();
    }
}