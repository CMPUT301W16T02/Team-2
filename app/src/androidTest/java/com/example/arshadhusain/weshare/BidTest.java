package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-14.
 */
public class BidTest extends ActivityInstrumentationTestCase2 {

    String bidder = "Mr.Fake";
    Double amount = 60.00;
    String itemOwner = "Mr.Real";
    String itemDescription = "Great";
    String item = "Item";

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public BidTest() {
        super(Bid.class);
    }

    public void testBid(){
        Bid fakeBid = new Bid(bidder, amount, item, itemOwner, itemDescription);

        assertEquals(fakeBid.getBidder(), "Mr.Fake");
        assertEquals(fakeBid.getAmount(), 60.00);
        assertEquals(fakeBid.getItemOwner(), "Mr.Real");
        assertEquals(fakeBid.getItemDescription(), "Great");
        assertEquals(fakeBid.getItem(), "Item");
    }
}
