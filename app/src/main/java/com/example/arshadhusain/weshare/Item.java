package com.example.arshadhusain.weshare;

import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by hasan on 2016-03-10.
 */
public class Item {
    private EditText name;
    private Integer status;
    private EditText description;
    private ArrayList<Bid> listBid = new ArrayList<Bid>();
    private EditText owner;
    private String borrower;

    public Item(EditText name, EditText description, EditText owner){
        this.name = name;
        this.status = 0;
        this.description = description;
        this.listBid = new ArrayList<Bid>();
        this.owner = owner;
        this.borrower = "";
    }

    public void setName(EditText name) {
        this.name = name;
    }

    public EditText getName() {
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
                break;
            case 1:
                statusString = "Bidded";
                break;
            case 2:
                statusString = "Borrowed by " + this.borrower;
                break;
        }
        return statusString;
    }

    public void setDescription(EditText description) {
        this.description = description;
    }

    public EditText getDescription() {
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

    public void setOwner(EditText owner) {
        this.owner = owner;
    }

    public EditText getOwner() {
        return owner;
    }

    @Override
    public String toString(){

        return name + "\n" + statusToString();
    }

    public void setDescription(String newDesc) {

    }
}