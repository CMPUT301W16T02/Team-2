package com.example.arshadhusain.weshare;

/**
 * Created by Overseer on 2016-03-10.
 */
public class MakeBid {
    private Bid model;

    public MakeBid(Bid model){
        this.model = model;
    }

    public void setBidBidder(String name){
        model.setBidder(name);
    }

    public String getBidBidder(){
        return model.getBidder();
    }

    public void setBidAmount(Double amount) {
        model.setAmount(amount);
    }

    public Double getBidAmount(){
        return model.getAmount();
    }

    public void setBidItem(String item){
        model.setItem(item);
    }

    public void getBidItem(){
        model.getItem();
    }

}