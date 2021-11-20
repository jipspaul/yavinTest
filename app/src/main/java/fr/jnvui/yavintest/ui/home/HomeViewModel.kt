package fr.jnvui.yavintest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.usecases.TicketUseCase

class HomeViewModel(private val ticketUseCase: TicketUseCase) :
    ViewModel() {

    private val _tickets = ticketUseCase.getTickets()
    val tickets: LiveData<List<Ticket>> = _tickets

    private val _ticketsCart = MutableLiveData<List<Ticket>>()
    val ticketsCart: LiveData<List<Ticket>> = _ticketsCart

    private val cartList : ArrayList<Ticket> = arrayListOf()

    fun addTicketToCart(ticket: Ticket) {
        cartList.add(ticket)
        _ticketsCart.value = cartList
    }
}

class HomeViewModelFactory(
    private val ticketUseCase: TicketUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(ticketUseCase) as T
    }
}