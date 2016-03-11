package com.example.arshadhusain.weshare;

import java.util.ArrayList;

/**
 * Created by hasan on 2016-03-10.
 */
public class Item {
    private String name;
    private Integer status;
    private String description;
    private ArrayList<Bid> listBid = new ArrayList<Bid>();
    private String owner;
    private String borrower;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addBid(Bid bid) {
        this.listBid.add(bid);
    }

    public void deleteBid(Bid bid) {
        this.listBid.remove(bid);
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}
