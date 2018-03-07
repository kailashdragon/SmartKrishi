package com.example.kailashpatel.smartkrishi;

/**
 * Created by Kailash Patel on 26-01-2018.
 */


public class Farmer {
    private String name;
    private String address;
    private String dob;
    private String phone;
    public Farmer(String name, String address, String dob, String phone){
        this.name=name;
        this.address=address;
        this.dob=dob;
        this.phone=phone;

    }
    public String getName(){return name;}
    private void setName(String name){this.name=name;}
    public String getAddress(){return address;}
    private void setAddress(String address){this.address=address;}
    public String getDate(){return dob;}
    private void setDate(String dob){this.dob=dob;}
    public String getPhone(){return phone;}
    private void setPhone(String phone){this.phone=phone;}
}
