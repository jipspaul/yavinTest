package fr.jnvui.yavintest.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.usecases.TicketUseCase
import kotlinx.android.synthetic.main.payment_fragment.*
import org.koin.android.ext.android.inject

class PaymentFragment : Fragment() {

    companion object {
        val TICKET_ID_INTENT_EXTRA = "TICKET_ID_INTENT_EXTRA"
        fun newInstance() = PaymentFragment()
    }

    private lateinit var viewModel: PaymentViewModel
    private val ticketUseCase: TicketUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.payment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(
                this,
                PaymentViewModelFactory(ticketUseCase)
            ).get(PaymentViewModel::class.java)

        activity?.intent?.extras?.getString(TICKET_ID_INTENT_EXTRA)?.let { it ->
            viewModel.getTicketById(it).observe(this.requireActivity(),
                { ticket ->
                    showTicketInformation(ticket)
                }
            )
        }

    }

    fun showTicketInformation(ticket: Ticket) {
        ticketTypeTextView.text = ticket.ticketType
        ticketPriceTextView.text = ticket.ticketPrice

    }

}