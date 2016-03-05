package com.example.arshadhusain.weshare;

/**
 * Created by Hanson on 2016-03-05.
 */
public class Bid {
    private String bidder;
    private Double amount;

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String getBidder() {
        return bidder;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
