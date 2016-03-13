package com.example.arshadhusain.weshare;

import android.content.Intent;

/**
 * Created by Overseer on 2016-03-10.
 */

//Bid controller class
public class MakeBid {

    //http://www.101apps.co.za/index.php/articles/passing-data-between-activities.html 2016-03-12
    Intent intentBid = getIntent();

    //Check to see if intent contains values
    if(intentBid != null){
        String name = intent.getStringExtra(KEY);
        Double amount = intentBid.getDoubleExtra(KEY);
        String item = intentBid.getStringExtra(KEY);
    }

    private Bid model;  //Bid model

    //Setter for model
    public MakeBid(Bid model){
        this.model = model;
    }

    //Setter for Bid Bidder
    public void setBidBidder(String name){
        model.setBidder(name);
    }

    //Getter for Bid Bidder
    public String getBidBidder(){
        return model.getBidder();
    }

    //Setter for Bid Amount
    public void setBidAmount(Double amount) {
        model.setAmount(amount);
    }

    //Getter for Bid Amount
    public Double getBidAmount(){
        return model.getAmount();
    }

    //Setter for Bid Item
    public void setBidItem(String item){
        model.setItem(item);
    }

    //Getter for Bid Item
    public void getBidItem(){
        model.getItem();
    }

    public void addBid() {
        
    }

}