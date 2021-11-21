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

    }
}