package com.example.arshadhusain.weshare;

/**
 * Created by Hanson on 2016-03-05.
 */
public class Account {
    private String username;
    private String email;
    private String city;
    //add password?

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

}
