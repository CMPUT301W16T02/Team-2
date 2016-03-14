package com.example.arshadhusain.weshare;

import android.net.Network;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-12.
 */
public class MakeBidActivityTest extends ActivityInstrumentationTestCase2 {
    String bidder;
    Double amount;

    public MakeBidActivityTest(){
        super(MakeBidActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void makeBidTest(){
        Bid bid = new Bid(bidder, amount);
        assertTrue(bid.hasBidder());
        assertTrue(bid.hasAmount());

        bid.setAmount((double) 10);


    }

    /*
    public void testNetworkConnection() {
        // tests the network connection
        // if cannot connect, stores the bid data locally

        Network network = new Network();
        try {
            network.connectToServer();
        } catch (NoConnectionException e) {
            System.out.println("Cannot connect to the server");
            saveInFile(bid.getItemName(), bid.getOwnerName(), bid.getOfferRate);
        }
    }*/

}
