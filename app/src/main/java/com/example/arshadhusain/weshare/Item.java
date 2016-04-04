package com.example.arshadhusain.weshare;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ArrayAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import io.searchbox.annotations.JestId;

/**
 * Item model class.
 * Defines setters and getters for item object.
 *
 * @Author Hanson, Chris
 * @Version 1.0
 */
public class Item {
    @JestId
    protected String id;
    protected transient Bitmap thumbnail;
    protected String thumbnailBase64;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Item(String name, String description, String owner, Bitmap thumbnail){
        this.name = name;
        this.status = 0;
        this.description = description;
        this.listBid = new ArrayList<Bid>();
        this.owner = owner;
        this.borrower = "";
        this.thumbnail = thumbnail;
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

        return name + "\n" + statusToString();
    }

    public void addThumbnail(Bitmap newThumbnail){
        if (newThumbnail != null) {
            thumbnail = newThumbnail;

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            newThumbnail.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

            byte[] b = byteArrayOutputStream.toByteArray();
            thumbnailBase64 = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }

    public void removeThumbnail(){
        thumbnail = null;
        thumbnailBase64 = null;
    }

    public Bitmap getThumbnail(){
        if (thumbnail == null && thumbnailBase64 != null){
            byte[] decodeString = Base64.decode(thumbnailBase64, Base64.DEFAULT);
            thumbnail = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        }
        return thumbnail;
    }

}
