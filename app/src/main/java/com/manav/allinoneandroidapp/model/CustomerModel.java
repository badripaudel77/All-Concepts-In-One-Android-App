package com.manav.allinoneandroidapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CustomerModel {

    @PrimaryKey(autoGenerate = true)
    private int id; //table id
    private int customerID; //customer's id
    private String customerName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
