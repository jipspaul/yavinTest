package fr.jnvui.yavintest

import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.utils.TicketsUtils.Companion.formatPrice
import fr.jnvui.yavintest.utils.TicketsUtils.Companion.getPriceFromTicketList
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsUnitTest {

    @Test
    fun calculatePriceFromTicketList() {

        val ticketsListEqual1 = listOf(
            Ticket("", "", "1", 1),
        )
        assertEquals(getPriceFromTicketList(ticketsListEqual1), 1.0, 0.001)

        val ticketsListEqual20 = listOf(
            Ticket("", "", "1", 10),
            Ticket("", "", "1", 10),

            )
        assertEquals(getPriceFromTicketList(ticketsListEqual20), 20.0, 0.001)

        val ticketsListEqual33 = listOf(
            Ticket("", "", "1", 1),
            Ticket("", "", "1", 1),
            Ticket("", "", "1", 1),
            Ticket("", "", "1", 30),
            )
        assertEquals(getPriceFromTicketList(ticketsListEqual33), 33.0, 0.001)

    }

    @Test
    fun testStringFormatter(){

        assertEquals(formatPrice(0.0),"0.00")
        assertEquals(formatPrice(12.2222),"12.22")

    }

}