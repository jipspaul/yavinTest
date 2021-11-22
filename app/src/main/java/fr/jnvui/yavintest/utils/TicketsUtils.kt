package fr.jnvui.yavintest.utils

import fr.jnvui.yavintest.models.Ticket

class TicketsUtils {
    companion object {

        fun getNumberOfTicketFromList(tickets: List<Ticket>): Int {
            var numberOfTickets: Int = 0
            for (t in tickets) {
                numberOfTickets += t.ticketCartCounter
            }
            return numberOfTickets
        }

        fun getPriceFromTicketList(tickets: List<Ticket>): Double {
            var total = 0.0
            for (t in tickets) {
                total += (t.ticketPrice.toDouble() * t.ticketCartCounter.toDouble())
            }
            return total
        }

        fun formatPrice(price: Double): String{
            return String.format("%.2f", price)
        }

    }
}