package com.example.arshadhusain.weshare;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Hanson on 2016-03-05.
 */
public class Item {
    private String name;
    private Integer status;
    private String description;
    private ArrayList<Bid> listBid = new ArrayList<Bid>();
    private String owner;
    private String borrower;

    public Item(String name, String description, String owner){
        this.name = name;
        this.status = 0;
        this.description = description;
        this.listBid = new ArrayList<Bid>();
        this.owner = owner;
        this.borrower = "";
    }

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

    public String statusToString(){

        String statusString = "";

        switch (this.status) {
            case 0:
                statusString = "Available";
            case 1:
                statusString = "Bidded";
            case 2:
                statusString = "Borrowed by " + this.borrower;
        }
        return statusString;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Bid> getListBid() {
        return this.listBid;
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

    @Override
    public String toString(){

        return name + " | " + statusToString();
    }
}
