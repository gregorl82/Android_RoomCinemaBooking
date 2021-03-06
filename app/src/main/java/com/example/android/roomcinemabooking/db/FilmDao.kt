package com.example.android.roomcinemabooking.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.example.android.roomcinemabooking.model.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM films")
    fun findAllFilms(): List<Film>

    @Query("SELECT * FROM films WHERE id = :id")
    fun findFilmById(id: Long): Film

    @Insert(onConflict = IGNORE)
    fun insertFilm(film: Film): Long

    @Update(onConflict = REPLACE)
    fun updateFilm(film: Film)

    @Delete
    fun deleteFilm(film: Film)
}