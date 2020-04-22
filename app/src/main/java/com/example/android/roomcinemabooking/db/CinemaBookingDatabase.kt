package com.example.android.roomcinemabooking.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.roomcinemabooking.model.Customer
import com.example.android.roomcinemabooking.model.Film

@Database(entities = arrayOf(Customer::class, Film::class), version = 2)
abstract class CinemaBookingDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun filmDao(): FilmDao

    companion object {

        private var instance: CinemaBookingDatabase? = null

        fun getInstance(context: Context): CinemaBookingDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    CinemaBookingDatabase::class.java,
                    "CinemaBooking"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance as CinemaBookingDatabase
        }

    }

}