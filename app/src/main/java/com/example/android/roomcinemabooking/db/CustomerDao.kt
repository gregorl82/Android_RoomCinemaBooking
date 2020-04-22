package com.example.android.roomcinemabooking.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
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

    @Delete
    fun deleteCustomer(customer: Customer)

}