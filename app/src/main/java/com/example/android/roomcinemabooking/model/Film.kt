package com.example.android.roomcinemabooking.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Film (
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var title: String,
    var price: Int
)