package fr.jnvui.yavintest.usecases

import androidx.lifecycle.LiveData
import fr.jnvui.yavintest.dao.TicketsDAO
import fr.jnvui.yavintest.models.Ticket

class TicketUseCase(private val ticketDao: TicketsDAO) {


    fun getTickets(): LiveData<List<Ticket>> {
        return ticketDao.getAll()
    }

    fun getTicketById(id: String): LiveData<Ticket> {
        return ticketDao.loadSingle(id)
    }

    fun updateTicketPrice(ticket: Ticket) {
        ticketDao.update(ticket)
    }
}