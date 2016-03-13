
package com.example.arshadhusain.weshare;

/**
 * Created by Hanson on 2016-03-05.
 */

//Bid model class
public class Bid {
    private String bidder;  //User placing the bid
    private Double amount;  //Bid amount
    private String item;    //Item bid being place on

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
}
