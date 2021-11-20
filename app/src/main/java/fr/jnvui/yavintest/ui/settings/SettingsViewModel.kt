package fr.jnvui.yavintest.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.usecases.TicketUseCase

class SettingsViewModel(private val ticketUseCase: TicketUseCase) : ViewModel() {

    private val _tickets = ticketUseCase.getTickets()
    val tickets: LiveData<List<Ticket>> = _tickets
}

class SettingsViewModelFactory(
    private val ticketUseCase: TicketUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(ticketUseCase) as T
    }
}