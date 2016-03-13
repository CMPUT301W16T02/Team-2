package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-12.
 */
public class AddItemActivityTest extends ActivityInstrumentationTestCase2 {
    public AddItemActivityTest() {
        super(AddItemActivity.class);
    }

    public void testAddItemActivity(){
        Item item = new Item("Textbook", "Like new", "HasanBadran");
        assertNotNull(item);
    }
}
