package fr.jnvui.yavintest

import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.models.Transaction
import fr.jnvui.yavintest.utils.TicketsUtils.Companion.formatPrice
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class modelUnitTest {

    @Test
    fun ticketModel() {
        val ticket = Ticket("1", "1", "1", 1)
        assertEquals(ticket.id, "1")
        assertEquals(ticket.ticketPrice, "1")
        assertEquals(ticket.ticketType, "1")
        assertEquals(ticket.ticketCartCounter, 1)
    }

    @Test
    fun transactionModel() {
        val transaction = Transaction(
            "b14fbe01-fd2a-419b-9f4d-6aea4edf514b",
            "2020-01-15",
            "11:19:31",
            1000,
            "EUR",
            "DEBIT",
            "527345XXXXXX2315",
            "003",
            "VISA"
        )

        assertEquals(transaction.transactionID, "b14fbe01-fd2a-419b-9f4d-6aea4edf514b")
        assertEquals(transaction.date, "2020-01-15")
        assertEquals(transaction.time, "11:19:31")
        assertEquals(transaction.amount, 1000)
        assertEquals(transaction.currency, "EUR")
        assertEquals(transaction.status, "DEBIT")
        assertEquals(transaction.pan, "527345XXXXXX2315")
        assertEquals(transaction.logicalNumber, "003")
        assertEquals(transaction.scheme, "VISA")

    }

}