package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-12.
 */
public class AddItemActivityTest extends ActivityInstrumentationTestCase2 {
    public AddItemActivityTest() {
        super(AddItemActivity.class);
    }

    public void testAddItemToList()
    {
        Account Account1 = new Account("HasanBadran", "badran@gmail.com", "Edmonton");

        Account1.getAccount();
        assertTrue(accounts.contains(Account1));

        Item item = new Item("microwave", "Like new", AVAILABLE);
        things.add(item);
        assertTrue(Account1.things.hasItem(item));



    public void testAddItemActivity() {
        Item item = new Item("microwave", "Like new", AVAILABLE);
        try(item.setName("")){
            //
        }
        catch (InvalidArgumentException e){
            assertFalse(item.getName() == "");
        }

    }

    public void testSetDescription() {
        Item item = new Item("microwave", "Like new", AVAILABLE);
        try(item.setDescription("")){
            //
        }
        catch (InvalidArgumentException e){
            assertFalse(item.getDescription() == "");
        }
    }
}
