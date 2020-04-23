package com.example.android.roomcinemabooking.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.example.android.roomcinemabooking.model.Screening

@Dao
interface ScreeningDao {

    @Query("SELECT * FROM screenings")
    fun findAllScreenings(): List<Screening>

    @Query("SELECT * FROM screenings WHERE id = :id")
    fun findScreeningById(id: Long): Screening

    @Insert(onConflict = IGNORE)
    fun insertScreening(screening: Screening): Long

    @Update(onConflict = REPLACE)
    fun updateScreening(screening: Screening)

    @Delete
    fun deleteScreening(screening: Screening)

}