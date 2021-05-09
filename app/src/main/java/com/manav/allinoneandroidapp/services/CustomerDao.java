package com.manav.allinoneandroidapp.services;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.manav.allinoneandroidapp.model.CustomerModel;

import java.util.List;

//DAO interface
@Dao
public interface CustomerDao {

    // 1: Insert Customer
    @Insert
    public void insertCustomer(CustomerModel customerModel);

    //2: Get all customers
    @Query("SELECT * FROM CustomerModel")
    public List<CustomerModel> getAllCustomers();

    //3: Get one customer
    //@Query("SELECT * FROM CustomerModel where id = :id")
    //public void getOneCustomer(int id);

    //4 : Update Customer
    @Update
    public void updateCustomer(CustomerModel customerModel);

    //5: Delete customer
    @Delete
    public void deleteCustomer(CustomerModel customerModel); //we can also delete via id

}
