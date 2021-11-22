package fr.jnvui.yavintest.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.ui.adapters.AdapterClickListener
import fr.jnvui.yavintest.ui.adapters.TicketAdapter
import fr.jnvui.yavintest.ui.payment.PaymentActivity
import fr.jnvui.yavintest.ui.payment.PaymentFragment.Companion.TOTAL_PRICE_INTENT_EXTRA
import fr.jnvui.yavintest.usecases.TicketUseCase
import fr.jnvui.yavintest.utils.TicketsUtils.Companion.formatPrice
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val ticketUseCase: TicketUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(
                this,
                HomeViewModelFactory(ticketUseCase)
            ).get(HomeViewModel::class.java)

        homeViewModel.tickets.observe(viewLifecycleOwner, Observer {
            val llm = LinearLayoutManager(this.requireContext())
            llm.orientation = LinearLayoutManager.VERTICAL
            ticketsRecyclerView.layoutManager = llm
            ticketsRecyclerView.adapter = TicketAdapter(
                it.toTypedArray(),
                AdapterClickListener(
                    {
                        homeViewModel.updateTicket(it)
                    },
                    {
                        homeViewModel.removeTicketFromCart(it)
                    })
            )
            //update cart counter
            homeViewModel.updateCart(it)

        })

        homeViewModel.ticketsCart.observe(viewLifecycleOwner, Observer {
            (formatPrice(it) + "€").also { cartCountTextView.text = it }
        })

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartIcon.setOnClickListener {
            showPaymentActivity()
        }
    }

    fun showPaymentActivity() {
        val intent = Intent(this.context, PaymentActivity::class.java)
        intent.putExtra(TOTAL_PRICE_INTENT_EXTRA, homeViewModel.totalPrice.toString())
        startActivity(intent)
    }
}