package com.example.arshadhusain.weshare;

import java.util.ArrayList;

import io.searchbox.annotations.JestId;

/**
 * Model class for account. Defines setters and getters for 'account'
 *
 * @Author: Arshad, Hanson
 * @Version: 1.0
 **/

public class Account {
    @JestId
    protected String id;
    private ArrayList<Float> rateArray = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String username;
    private String email;
    private String city;

    Account(String username, String email, String city)
    {
        this.username = username;
        this.email = email;
        this.city = city;

    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }


    public void addRate(float rate) {
        rateArray.add(rate);
    }


    public double showAverage() {

        if ((rateArray == null ) || (rateArray.size() == 0) ){
            return 0.0;
        }else{

            float sum = 0;
            for (Float rate: rateArray){
                sum+= rate;
            }
            return sum/rateArray.size();
        }

    }

}
