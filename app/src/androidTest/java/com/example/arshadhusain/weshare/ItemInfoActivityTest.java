package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hanson on 2016-03-14.
 */
public class ItemInfoActivityTest extends ActivityInstrumentationTestCase2 {
    public ItemInfoActivityTest() {super(ItemInfoActivity.class);}

    public void testAddBid() {

        // start app
        ItemInfoActivity activity =(ItemInfoActivity) getActivity();

        final EditText bidText = activity.getBidAmount();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bidText.setText("27");
            }
        });
        getInstrumentation().waitForIdleSync();

        final Button addBidButton = activity.getViewOwner();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addBidButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

    }
}
