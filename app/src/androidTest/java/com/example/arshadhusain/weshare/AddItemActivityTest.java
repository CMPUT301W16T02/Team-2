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
        Account account = new Account("HasanBadran", "badran@gmail.com", "Edmonton");

        Item item = new Item("microwave", "Like new", "HasanBadran");
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item);
        assertTrue(items.contains(items));

    }

    public void testAddItemActivity() {
        Item newItem = new Item("microwave", "Like new", "HassanBadran");

        newItem.setName("ArshadHusain");
        assertTrue(newItem.getName().equals("ArshadHusain"));

    }

    public void testSetDescription() {
        Item item = new Item("microwave", "Like new", "ArshadHusain");
        item.setDescription("Very old");

        assertTrue(item.getDescription().equals("Very old"));

    }

}
