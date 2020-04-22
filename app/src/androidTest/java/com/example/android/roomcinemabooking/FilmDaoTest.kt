package com.example.android.roomcinemabooking

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.roomcinemabooking.db.CinemaBookingDatabase
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilmDaoTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: CinemaBookingDatabase
    private lateinit var filmDao: FilmDao
    private lateinit var film1: Film

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        try {
            database = Room.inMemoryDatabaseBuilder(context, CinemaBookingDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        } catch (e: Exception) {
            Log.i(this.javaClass.simpleName, e.message)
        }
        filmDao = database.filmDao()

        film1 = Film()
        filmDao.insertFilm(film1)
    }

    @Test
    fun testFindAll() {
        val numberOfFilms = filmDao.findAllFilms().size
        Assert.assertEquals(1, numberOfFilms)
    }

    @After
    fun tearDown() {
        database.close()
    }
}