package com.example.android.roomcinemabooking.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.android.roomcinemabooking.model.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM films")
    fun findAllFilms(): List<Film>

    @Insert(onConflict = IGNORE)
    fun insertFilm(film: Film): Long
}