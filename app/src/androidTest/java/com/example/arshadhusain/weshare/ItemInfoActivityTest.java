package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Hanson on 2016-03-14.
 */
public class ItemInfoActivityTest extends ActivityInstrumentationTestCase2 {
    public ItemInfoActivityTest() {
        super(ItemInfoActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testActivityExists() {
        ItemInfoActivity activity = (ItemInfoActivity) getActivity();
        assertNotNull(activity);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


//    public void testAddBid() {
//
//        // start app
//        ItemInfoActivity activity =(ItemInfoActivity) getActivity();
//
//        final EditText bidText = activity.getBidAmount();
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                bidText.setText("27");
//            }
//        });
//        getInstrumentation().waitForIdleSync();
//
//        final Button addBidButton = activity.getViewOwner();
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                addBidButton.performClick();
//            }
//        });
//        getInstrumentation().waitForIdleSync();
//
//    }
}
