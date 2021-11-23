package fr.jnvui.yavintest

import android.content.Context
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import fr.jnvui.yavintest.dao.AppDatabase
import fr.jnvui.yavintest.dao.TicketsDAO
import fr.jnvui.yavintest.models.Ticket
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException


@RunWith(JUnit4::class)
class DaoUnitTest {
    private lateinit var ticketDao: TicketsDAO
    private lateinit var db: AppDatabase

    @Mock
    private lateinit var observer: Observer<Ticket>

    @Before
    fun createDb() {
        MockitoAnnotations.initMocks(this);
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        ticketDao = db.ticketDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun checkIfDaoEntryWork() = runBlocking {
        val ticket = Ticket("1", "", "", 1)
        ticketDao.insertAll(ticket)
        val byName = ticketDao.loadSingle2("1")
        Assert.assertEquals(byName.id, "1")

    }

    @Test
    @Throws(Exception::class)
    fun checkIfDaoUpdateWork() = runBlocking {
        val ticket = Ticket("1", "", "1", 1)
        ticketDao.insertAll(ticket)
        val ticketFromDao = ticketDao.loadSingle2("1")
        Assert.assertEquals(ticketFromDao.ticketPrice, "1")

        val ticket2 = Ticket("1", "", "2", 1)
        ticketDao.update(ticket2)
        val ticketFromDao2 = ticketDao.loadSingle2("1")
        Assert.assertEquals(ticketFromDao2.ticketPrice, "2")

    }

}