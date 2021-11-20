package fr.jnvui.yavintest.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.usecases.TicketUseCase

class PaymentViewModel(private val ticketUseCase: TicketUseCase) : ViewModel() {

    fun getTicketById(id: String): LiveData<Ticket> {
        return ticketUseCase.getTicketById(id)
    }
}

class PaymentViewModelFactory(
    private val ticketUseCase: TicketUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PaymentViewModel(ticketUseCase) as T
    }
}