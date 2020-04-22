package com.example.android.roomcinemabooking.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var name: String,
    var funds: Int
)