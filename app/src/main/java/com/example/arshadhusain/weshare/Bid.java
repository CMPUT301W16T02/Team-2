
package com.example.arshadhusain.weshare;

/**
 * Created by Hanson on 2016-03-05.
 */

//Bid model class
public class Bid {
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
