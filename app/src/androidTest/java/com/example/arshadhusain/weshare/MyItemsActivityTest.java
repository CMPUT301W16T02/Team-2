package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-14.
 */
public class MyItemsActivityTest extends ActivityInstrumentationTestCase2 {
    String username;
    String city;
    String email;
    String name;
    String description;
    String owner;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public MyItemsActivityTest() {
        super(Item.class);
    }

    public void testActivityExists() {
        MyItemsActivity activity = (MyItemsActivity) getActivity();
        assertNotNull(activity);
    }

    // testing adding an item
    public void testAddItem(){
        Account testAccount = new Account(username, city, email);
        testAccount.setUsername("Hasan");
        testAccount.setCity("Edmonton");
        testAccount.setEmail("badran@ualberta.ca");

        Item item = new Item(name, description, owner);

        item.setName("testItem");
        item.setDescription("testDescription");
        item.setOwner("testOwner");

        assertTrue(item.getName().equals("testItem"));
        assertTrue(item.getDescription().equals("testDescription"));
        assertTrue(item.getOwner().equals("testOwner"));
    }

    // testing creation of an item with no name
    public void testSetNameOfItem(){
        Account testAccount = new Account(username, city, email);
        testAccount.setUsername("Hasan");
        testAccount.setCity("Edmonton");
        testAccount.setEmail("badran@ualberta.ca");

        Item item = new Item(name, description, owner);

        item.setDescription("testDescription");
        item.setOwner("testOwner");

        try{
            item.setName("");
        }catch(IllegalArgumentException e){
            assertFalse(item.getName().equals(""));
        }
    }

    // testing creation of an item with no description
    public void testSetDescriptionOfItem(){
        Account testAccount = new Account(username, city, email);
        testAccount.setUsername("Hasan");
        testAccount.setCity("Edmonton");
        testAccount.setEmail("badran@ualberta.ca");

        Item item = new Item(name, description, owner);

        item.setName("testItem");
        item.setOwner("testOwner");

        try{
            item.setDescription("");
        }catch(IllegalArgumentException e){
            assertFalse(item.getDescription().equals(""));
        }
    }

    // testing creation of an item with no owner
    public void testSetOwnerOfItem(){
        Account testAccount = new Account(username, city, email);
        testAccount.setUsername("Hasan");
        testAccount.setCity("Edmonton");
        testAccount.setEmail("badran@ualberta.ca");

        Item item = new Item(name, description, owner);

        item.setName("testItem");
        item.setDescription("testDescription");

        try{
            item.setOwner("");
        }catch(IllegalArgumentException e){
            assertFalse(item.getOwner().equals(""));
        }
    }
}
