package com.example.arshadhusain.weshare;

import android.net.Network;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-12.
 */
public class MakeBidActivityTest extends ActivityInstrumentationTestCase2 {

    public MakeBidActivityTest(){
        super(MakeBidActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void makeBidTest(){
        // tests the makeBid method

        Network network = new Network();
        network.connectToServer();

        Bid bid = new Bid('item name', 'owner username', 10);
        assertTrue(bid.hasItemName());
        assertTrue(bid.hasOwnerName());
        assertTrue(bid.hasOfferRate());

        try {
            bid.submit();
        } catch (BlankFieldException e) {
            displayError('Must fill in all required fields')
        }
    }

    public void testNetworkConnection() {
        // tests the network connection
        // if cannot connect, stores the bid data locally

        Network network = new Network();
        try {
            network.connectToServer();
        } catch (NoConnectionException e) {
            displayError('Cannot connect to the server');
            saveInFile(bid.getItemName(), bid.getOwnerName(), bid.getOfferRate);
        }
    }

}
