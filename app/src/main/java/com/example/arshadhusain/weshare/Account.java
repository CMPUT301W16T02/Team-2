package com.example.arshadhusain.weshare;

/**
 * Created by hasan on 2016-03-10.
 */
public class Account {
    private static String username;
    private String email;
    private String city;

    public Account(String username, String email, String city) {

    }

    public void setUsername(String username) throws TooLongException, NoSpacesException {
        if(!username.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            if(username.contains(" ")){
                throw new NoSpacesException();
            }else {
                if (username.length() > 30) {

                    throw new TooLongException();
                }else{
                    this.username = username;
                }
            }
        }
    }

    public static String getUsername() {
        return username;
    }

    public void setEmail(String email) throws IllegalEmailException, NoSpacesException, TooLongException {
        if (email.isEmpty()) {
            throw new IllegalArgumentException();
        }else{
            //must have a valid e-mail
            if(!email.contains("@")){
                throw new IllegalEmailException();
            }else{
                if(email.contains(" ")){
                    throw new NoSpacesException();
                }else {
                    if (email.length() > 30) {
                        throw new TooLongException();
                    }else{
                        this.email = email;
                    }
                }
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setCity(String city) {
        if (city.isEmpty()) {
            throw new IllegalArgumentException();
        }else {
            if (city.length() > 30) {
            }else{
                this.city = city;
            }
        }
    }

    public String getCity() {
        return city;
    }

}
