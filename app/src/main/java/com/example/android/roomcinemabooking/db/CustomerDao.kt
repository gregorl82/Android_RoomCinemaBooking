package com.example.android.roomcinemabooking.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.android.roomcinemabooking.model.Customer

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers")
    fun findAllCustomers(): List<Customer>

    @Insert(onConflict = IGNORE)
    fun insertCustomer(customer: Customer)

}