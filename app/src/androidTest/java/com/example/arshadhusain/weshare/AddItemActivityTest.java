package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by hasan on 2016-03-12.
 */
public class AddItemActivityTest extends ActivityInstrumentationTestCase2 {
    public AddItemActivityTest() {
        super(AddItemActivity.class);
    }

    /*public void testAddItem() {
        AddItemActivity activity = (AddItemActivity) getActivity();
    }*/



    public void testAddItem() {
        Account Account1 = new Account("HasanBadran", "badran@gmail.com", "Edmonton");

        Item item = new Item("microwave", "Like new", "HasanBadran");
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item);
        assertTrue(items.contains(items));

    }

    public void testAddItemActivity() {
        Item hello = new Item("microwave", "Like new", "HassanBadran");

        hello.setName("ArshadHusain");


        assertTrue(hello.getName().equals("ArshadHusain"));


    }

    public void testSetDescription() {
        Item item = new Item("microwave", "Like new", "ArshadHusain");
        item.setDescription("Very old");

        assertTrue(item.getDescription().equals("Very old"));

    }

}
