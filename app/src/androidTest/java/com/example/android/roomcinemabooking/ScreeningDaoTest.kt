package com.example.android.roomcinemabooking

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.roomcinemabooking.db.CinemaBookingDatabase
import com.example.android.roomcinemabooking.db.FilmDao
import com.example.android.roomcinemabooking.db.ScreeningDao
import com.example.android.roomcinemabooking.model.Film
import com.example.android.roomcinemabooking.model.Screening
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScreeningDaoTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: CinemaBookingDatabase
    private lateinit var screeningDao: ScreeningDao
    private lateinit var filmDao: FilmDao
    private lateinit var screening1: Screening
    private lateinit var savedFilm: Film

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
        screeningDao = database.screeningDao()
        filmDao = database.filmDao()

        val film1 = Film(null, "Jurassic World: Fallen Kingdom", 875)
        filmDao.insertFilm(film1)

        savedFilm = filmDao.findFilmById(1)

        screening1 = Screening(null, "15:45", 35, savedFilm.id)
        screeningDao.insertScreening(screening1)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testFindAllScreenings() {
        val numberOfScreenings = screeningDao.findAllScreenings().size
        Assert.assertEquals(1, numberOfScreenings)
    }

    @Test
    fun testFindScreeningById() {
        val savedScreening = screeningDao.findScreeningById(1)
        Assert.assertEquals("15:45", savedScreening.screeningTime)
        Assert.assertEquals(35, savedScreening.capacity)
        Assert.assertEquals(1L, savedScreening.filmId)
        Assert.assertNotNull(savedScreening.id)
    }

    @Test
    fun testInsertScreenings() {
        val previousNumberOfScreenings = screeningDao.findAllScreenings().size

        val newScreening = Screening(null,"20:00", 50, savedFilm.id)
        screeningDao.insertScreening(newScreening)

        val currentNumberOfScreenings = screeningDao.findAllScreenings().size
        val numberOfNewScreenings = currentNumberOfScreenings - previousNumberOfScreenings

        Assert.assertEquals(1, numberOfNewScreenings)
    }

    @Test
    fun testUpdateScreening() {
        val savedScreening = screeningDao.findScreeningById(1)

        savedScreening.capacity = 20
        screeningDao.updateScreening(savedScreening)

        val updatedScreening = screeningDao.findScreeningById(1)

        Assert.assertEquals("15:45", updatedScreening.screeningTime)
        Assert.assertEquals(20, updatedScreening.capacity)
        Assert.assertEquals(1L, updatedScreening.filmId)
    }

    @Test
    fun testDeleteScreening() {
        val previousNumberOfScreenings = screeningDao.findAllScreenings().size

        val savedScreening = screeningDao.findScreeningById(1)
        screeningDao.deleteScreening(savedScreening)

        val currentNumberOfScreenings = screeningDao.findAllScreenings().size
        val numberOfScreeningsDeleted = previousNumberOfScreenings - currentNumberOfScreenings

        val deletedScreeningRef = screeningDao.findScreeningById(1)

        Assert.assertEquals(1, numberOfScreeningsDeleted)
        Assert.assertNull(deletedScreeningRef)
    }

    @Test
    fun testCascadeDelete() {
        filmDao.deleteFilm(savedFilm)
        val savedScreening = screeningDao.findScreeningById(1)

        Assert.assertNull(savedScreening)
    }
}