package com.example.android.roomcinemabooking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "screenings",
    foreignKeys = [ForeignKey(
        entity = Film::class,
        parentColumns = ["id"],
        childColumns = ["film_id"],
        onDelete = CASCADE
    )]
)
data class Screening(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    @ColumnInfo(name = "screening_time")
    var screeningTime: String,
    var capacity: Int,
    @ColumnInfo(name = "film_id")
    var filmId: Long?
)