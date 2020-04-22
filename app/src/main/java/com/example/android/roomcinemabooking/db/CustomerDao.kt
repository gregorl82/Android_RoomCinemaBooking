package com.example.android.roomcinemabooking.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.android.roomcinemabooking.model.Customer

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers")
    fun findAllCustomers(): List<Customer>

    @Query("SELECT * FROM customers WHERE id = :id")
    fun findCustomerById(id: Long): Customer

    @Insert(onConflict = IGNORE)
    fun insertCustomer(customer: Customer)

    @Update(onConflict = REPLACE)
    fun updateCustomer(customer: Customer)

}