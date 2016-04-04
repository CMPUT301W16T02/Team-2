package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by hasan on 2016-03-12.
 */
public class AddItemActivityTest extends ActivityInstrumentationTestCase2 {
    ArrayList<Item> allItems = new ArrayList<Item>();
    public AddItemActivityTest() {
        super(AddItemActivity.class);
    }

    /*public void testAddItem() {
        AddItemActivity activity = (AddItemActivity) getActivity();
    }*/



    public void testAddItem() {
        Account account = new Account("HasanBadran", "badran@gmail.com", "Edmonton");

        Item item = new Item("microwave", "Like new", "HasanBadran");

        //ArrayList<Item> items = new ArrayList<Item>();
        //items.add(item);
        //assertTrue(items.contains(items));

        //View view;

        ElasticSearchAppController.AddItemTask addItemTask = new ElasticSearchAppController.AddItemTask();
        addItemTask.execute(item);

        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");

        try {
            allItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int i = 0;
        while(i < allItems.size()) {
            if(allItems.get(i).getName().equals(item.getName())) {
                assertTrue(allItems.get(i).equals(item));
                break;
            }

        }


    }

    public void testAddItemActivity() {
        Item newItem = new Item("microwave", "Like new", "HassanBadran");

        //newItem.setName("ArshadHusain");
        //assertTrue(newItem.getName().equals("ArshadHusain"));



    }

    public void testSetDescription() {
        Item item = new Item("microwave", "Like new", "ArshadHusain");
        item.setDescription("Very old");

        assertTrue(item.getDescription().equals("Very old"));

    }

}
