package com.example.android.roomcinemabooking.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.android.roomcinemabooking.model.Screening

@Dao
interface ScreeningDao {

    @Query("SELECT * FROM screenings")
    fun findAllScreenings(): List<Screening>

    @Insert(onConflict = IGNORE)
    fun insertScreening(screening: Screening): Long

}