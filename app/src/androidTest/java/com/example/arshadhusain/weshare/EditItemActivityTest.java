package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> master
/**
 * Created by hasan on 2016-03-12.
 */
public class EditItemActivityTest extends ActivityInstrumentationTestCase2 {
<<<<<<< HEAD
    EditText username;
    EditText city;
    EditText email;
    EditText name;
    EditText description;
    EditText owner;
=======
    String username;
    String city;
    String email;
    String name;
    String description;
    String owner;
>>>>>>> master

    public EditItemActivityTest(){
        super(EditItemActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testUpdateName() {
<<<<<<< HEAD
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
=======
        username = "testName";
        city = "testCity";
        email = "test@email.ca";
        name = "Macbook Pro";
        description = "Mint condition";
        owner = "testname";

        ArrayList<Item> items = new ArrayList<Item>();
        Account fakeAccount = new Account(username, city, email);
        Item item = new Item(name, description, owner);
        item.setName("microwave");

        items.add(item);
        assertTrue(items.contains(item));

        String newItemName = "microwave";
        items.get(0).setName(newItemName);
        //assertTrue(fakeAccount.getItems().getItem(0).getName == newItemName);
        assertTrue((items.get(0).toString()).equals(newItemName));
    }

   /* public void testUpdateDescription() {
>>>>>>> master
        Account fakeAccount = new Account(username, city, email);
        Item item = new Item(name, description, owner);
        item.description = "Like new";

        fakeAccount.addItem(item);
        assertTrue(fakeAccount.getItem.hasItem(item));

        String newItemDescription = "Like new";
        UpdateName(fakeAccount.getItems().getItem(0).setDescription, newItemName);
        assertTrue(fakeAccount.getItems().getItem(0).getDescription == newItemName);
<<<<<<< HEAD
    }
=======
    }*/
>>>>>>> master
}
