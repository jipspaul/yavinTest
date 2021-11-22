package fr.jnvui.yavintest.utils

import fr.jnvui.yavintest.models.Ticket

class TicketsUtils {
    companion object {

        /**
         * Function to get the total price to pay
         * take all ticket on list and calculate the number of each one to get the total price
         */
        fun getPriceFromTicketList(tickets: List<Ticket>): Double {
            var total = 0.0
            for (t in tickets) {
                total += (t.ticketPrice.toDouble() * t.ticketCartCounter.toDouble())
            }
            return total
        }

        /**
         * Function to get string from double value with only two digit
         */
        fun formatPrice(price: Double): String {
            return String.format("%.2f", price).replace(',', '.')
        }

    }
}