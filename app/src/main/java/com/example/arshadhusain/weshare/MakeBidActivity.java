package com.example.arshadhusain.weshare;

/**
 * Created by hasan on 2016-03-12.
 */
public class MakeBidActivity {
    private Bid model;

    public MakeBidActivity(Bid model){
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

    /*
    public void setBidItem(String item){
        model.setItem(item);
    }

    public void getBidItem(){
        model.getItem();
    }*/
}
