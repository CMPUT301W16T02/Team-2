
package com.example.arshadhusain.weshare;

import io.searchbox.annotations.JestId;

/**
 * Bid Model class defines setters and getters for the "bid" item
 *
 *
 * @author: Hanson, Philip, Arshad
 * @version: 1.0
 */

public class Bid {
    @JestId
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String bidder;  //User placing the bid
    private Double amount;  //Bid amount
    private String item;    //Item bid being place on
    private String itemOwner;
    private String itemDescription;

    public Bid(String bidder, Double amount, String item, String itemOwner, String itemDescription) {
        this.bidder = bidder;
        this.amount = amount;
        this.item = item;
        this.itemOwner = itemOwner;
        this.itemDescription = itemDescription;
    }

    //Setter for bidder
    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    //Getter for bidder
    public String getBidder() {
        return bidder;
    }

    //Setter for amount
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    //Getter for amount
    public Double getAmount() {
        return amount;
    }

    //Setter for item
    public void setItem(String item){
        this.item = item;
    }

    //Getter for item
    public String getItem(){
        return item;
    }

    public String getItemOwner() {
        return itemOwner;
    }
    public String getItemDescription() {
        return itemDescription;
    }

    @Override
    public String toString(){

        return "Item: "+ item + "\n" + "Description: " + itemDescription + "\n" + "Item Owner: " + itemOwner + "\n" + "Bid Amount " + "$" + amount;
    }
}
