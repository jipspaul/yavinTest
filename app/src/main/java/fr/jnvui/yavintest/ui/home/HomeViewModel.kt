package fr.jnvui.yavintest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.usecases.TicketUseCase
import fr.jnvui.yavintest.utils.TicketsUtils
import org.jetbrains.anko.doAsync

class HomeViewModel(private val ticketUseCase: TicketUseCase) :
    ViewModel() {

    private var _tickets = ticketUseCase.getTickets()
    val tickets: LiveData<List<Ticket>> = _tickets

    private val _ticketsCart = MutableLiveData<Int>()
    val ticketsCart: LiveData<Int> = _ticketsCart

    fun updateTicket(ticket: Ticket) {
        ticket.ticketCartCounter = ticket.ticketCartCounter + 1
        doAsync {
            ticketUseCase.updateTicketPrice(ticket)
        }
    }

    fun updateCart(tickets: List<Ticket>) {
        _ticketsCart.value = TicketsUtils.getNumberOfTicketFromList(tickets)
    }

    fun removeTicketFromCart(ticket: Ticket) {
        ticket.ticketCartCounter = ticket.ticketCartCounter - 1
        doAsync {
            ticketUseCase.updateTicketPrice(ticket)
        }
    }

}

class HomeViewModelFactory(
    private val ticketUseCase: TicketUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(ticketUseCase) as T
    }
}