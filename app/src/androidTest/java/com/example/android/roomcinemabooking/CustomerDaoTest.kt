package com.example.android.roomcinemabooking

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.roomcinemabooking.db.CinemaBookingDatabase
import com.example.android.roomcinemabooking.db.CustomerDao
import com.example.android.roomcinemabooking.model.Customer
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CustomerDaoTest {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: CinemaBookingDatabase
    private lateinit var customerDao: CustomerDao
    private lateinit var customer1: Customer

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
        customerDao = database.customerDao()

        customer1 = Customer(null, "John Smith", 1000)
        customerDao.insertCustomer(customer1)
    }

    @Test
    fun testFindAll() {
        val numberOfCustomers = customerDao.findAllCustomers().size
        Assert.assertEquals(1, numberOfCustomers)
    }

    @After
    fun tearDown() {
        database.close()
    }
}