package com.example.kailashpatel.smartkrishi;

/**
 * Created by Kailash Patel on 09-02-2018.
 */

public class Labour {
    String id, name, phone, bf;

    public Labour(){

    }

    public Labour(String id, String name, String phone, String bf) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.bf = bf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }
}
