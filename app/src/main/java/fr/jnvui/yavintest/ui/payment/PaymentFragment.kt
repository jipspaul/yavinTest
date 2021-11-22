package fr.jnvui.yavintest.ui.payment

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.usecases.TicketUseCase
import fr.jnvui.yavintest.utils.PaymentStatus
import kotlinx.android.synthetic.main.payment_fragment.*
import org.json.JSONArray
import org.json.JSONException
import org.koin.android.ext.android.inject

class PaymentFragment : Fragment() {

    companion object {
        val TOTAL_PRICE_INTENT_EXTRA = "TICKET_ID_INTENT_EXTRA"
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

        activity?.intent?.extras?.getString(TOTAL_PRICE_INTENT_EXTRA)?.let { it ->
            initViewModel(it)
        }

        payButton.setOnClickListener {
            viewModel.getTicketById(viewModel.price).observe(this.requireActivity(),
                { ticket ->
                    startPaymentOnYavinPay()
                }
            )
        }
        payReturn.setOnClickListener {
            activity?.finish()
        }

    }

    fun initViewModel(price: String) {
        viewModel.price = price
        showPriceInformation(price)
    }

    fun showPriceInformation(price: String) {
        ticketPriceTextView.text = price
    }

    fun showPaymentStatus(paymentResult: PaymentStatus) {
        when (paymentResult) {
            PaymentStatus.ERROR -> paymentStatus.text = getText(R.string.payment_error)
            PaymentStatus.SUCCESS -> paymentStatus.text = getText(R.string.payment_error)
        }

    }

    private fun startPaymentOnYavinPay() {
        val intent = Intent()
        intent.component =
            ComponentName("com.yavin.macewindu", "com.yavin.macewindu.PaymentActivity")
        intent.putExtra("vendorToken", "342")
        intent.putExtra("amount", viewModel.price)
        intent.putExtra("currency", "EUR")
        intent.putExtra("transactionType", "Debit")
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
        getPaymentResultFromYavinApp.launch(intent)

    }

    val getPaymentResultFromYavinApp =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {

            it?.data?.extras?.let { bundle ->
                Log.d("clientTicket", bundle["clientTicket"].toString())
                Log.d("status", bundle["status"].toString())
                Log.d("signatureRequired", bundle["signatureRequired"].toString())
                Log.d("transactionId", bundle["transactionId"].toString())
                showPaymentStatus(PaymentStatus.SUCCESS)
                activity?.finish()
            }

            showPaymentStatus(PaymentStatus.ERROR)
        }

}