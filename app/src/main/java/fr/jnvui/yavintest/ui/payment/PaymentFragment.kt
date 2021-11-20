package fr.jnvui.yavintest.ui.payment

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.usecases.TicketUseCase
import kotlinx.android.synthetic.main.payment_fragment.*
import org.json.JSONArray
import org.json.JSONException
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
            initViewModel(it)
        }

        payButton.setOnClickListener {
            viewModel.getTicketById(viewModel.ticketId).observe(this.requireActivity(),
                { ticket ->
                    startPaymentOnYavinPay(ticket)
                }
            )

        }

    }

    fun initViewModel(id: String) {
        viewModel.ticketId = id
        viewModel.getTicketById(id).observe(this.requireActivity(),
            { ticket ->
                showTicketInformation(ticket)
            }
        )
    }

    fun showTicketInformation(ticket: Ticket) {
        ticketTypeTextView.text = ticket.ticketType
        ticketPriceTextView.text = ticket.ticketPrice
    }

    private fun startPaymentOnYavinPay(ticket: Ticket) {
        val intent = Intent()
        intent.component =
            ComponentName("com.yavin.macewindu", "com.yavin.macewindu.PaymentActivity")
        intent.putExtra("vendorToken", "342")
        intent.putExtra("amount", ticket.ticketPrice)
        intent.putExtra("yavin_secret", "1s4DMHdqcZg1CnovHt1EYaNkuFe5TeeLV1YehyXLMj1aq2e8kI")
        intent.putExtra("currency", "EUR")
        intent.putExtra("transactionType", "Debit")
        intent.putExtra("reference", "123545")
        intent.putExtra("client", "{\"phone\":\"0611223344\",\"email\":\"client@client.com\"}")
        val jArray =
            JSONArray("[\"hello printer\", \"this is a\", \"    wonderful\", \"        TICKET\"]")
        val receiptTicket = ArrayList<String>()
        for (i in 0 until jArray.length()) {
            try {
                receiptTicket.add(jArray.getString(i))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        intent.putExtra("receiptTicket", receiptTicket)
        startActivityForResult(intent, 1111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 1111) {
            // handle post payment response from Yavin PAY
            val bundle: Bundle = intent!!.extras!!
            Log.d("clientTicket", bundle["clientTicket"].toString())
            Log.d("status", bundle["status"].toString())
            Log.d("signatureRequired", bundle["signatureRequired"].toString())
            Log.d("transactionId", bundle["transactionId"].toString())
        }
    }
}