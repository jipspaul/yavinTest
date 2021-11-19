package fr.jnvui.yavintest.usecases

import fr.jnvui.yavintest.models.Ticket

class TicketUseCase {

    fun getTickets(): Array<Ticket> {
        return arrayOf(
            Ticket("Single Journey ticket", "1,60£"),
            Ticket("One day ticket", "12,40£"),
            Ticket("One week ticket", "47,90£")
        )
    }

}