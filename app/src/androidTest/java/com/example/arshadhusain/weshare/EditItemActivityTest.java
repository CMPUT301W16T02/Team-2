package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

/**
 * Created by hasan on 2016-03-12.
 */
public class EditItemActivityTest extends ActivityInstrumentationTestCase2 {
    EditText username;
    EditText city;
    EditText email;
    EditText name;
    EditText description;
    EditText owner;

    public EditItemActivityTest(){
        super(EditItemActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testUpdateName() {
        Account fakeAccount = new Account(username, city, email);
        Item item = new Item(name, description, owner);
        item.name = "microwave";

        fakeAccount.addItem(item);
        assertTrue(fakeAccount.getItem.hasItem(item));

        String newItemName = "microwave";
        UpdateName(fakeAccount.getItems().getItem(0), newItemName);
        assertTrue(fakeAccount.getItems().getItem(0).getName == newItemName);
    }

    public void testUpdateDescription() {
        Account fakeAccount = new Account(username, city, email);
        Item item = new Item(name, description, owner);
        item.description = "Like new";

        fakeAccount.addItem(item);
        assertTrue(fakeAccount.getItem.hasItem(item));

        String newItemDescription = "Like new";
        UpdateName(fakeAccount.getItems().getItem(0).setDescription, newItemName);
        assertTrue(fakeAccount.getItems().getItem(0).getDescription == newItemName);
    }
}
