package fr.jnvui.yavintest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.usecases.TicketUseCase

class HomeViewModel(private val ticketUseCase: TicketUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _tickets = MutableLiveData<Array<Ticket>>().apply {
        value = ticketUseCase.getTickets()
    }
    val tickets: LiveData<Array<Ticket>> = _tickets

}

class HomeViewModelFactory(
    private val ticketUseCase: TicketUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(ticketUseCase) as T
    }
}