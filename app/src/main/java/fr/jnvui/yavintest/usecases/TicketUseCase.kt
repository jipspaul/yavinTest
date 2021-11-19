package fr.jnvui.yavintest.usecases

import androidx.lifecycle.LiveData
import fr.jnvui.yavintest.dao.TicketsDAO
import fr.jnvui.yavintest.models.Ticket
import org.jetbrains.anko.doAsync

class TicketUseCase(private val ticketDao: TicketsDAO) {


    fun getTickets(): LiveData<List<Ticket>> {
        doAsync { //TODO remove
            ticketDao.insertAll(Ticket("1", "Single Journey ticket", "1,60£"))
            ticketDao.insertAll(Ticket("2", "One day ticket", "12,40£"))
            ticketDao.insertAll(Ticket("3", "One week ticket", "47,90£"))
        }

        return ticketDao.getAll()
    }

}