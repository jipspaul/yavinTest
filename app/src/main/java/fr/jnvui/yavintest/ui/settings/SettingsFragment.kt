package fr.jnvui.yavintest.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.ui.adapters.TicketAdapter
import fr.jnvui.yavintest.ui.adapters.TicketSettingsAdapter
import fr.jnvui.yavintest.ui.home.HomeViewModelFactory
import fr.jnvui.yavintest.usecases.TicketUseCase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private val ticketUseCase: TicketUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(
                this,
               SettingsViewModelFactory(ticketUseCase)
            ).get(SettingsViewModel::class.java)

        settingsViewModel.tickets.observe(viewLifecycleOwner,  {
            val llm = LinearLayoutManager(this.requireContext())
            llm.orientation = LinearLayoutManager.VERTICAL
            ticketsSettingsRecyclerView.layoutManager = llm
            ticketsSettingsRecyclerView.adapter = TicketSettingsAdapter(it.toTypedArray())
        })

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}