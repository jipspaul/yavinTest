package fr.jnvui.yavintest.ui.settings

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.ui.adapters.AdapterClickListener
import fr.jnvui.yavintest.ui.adapters.TicketSettingsAdapter
import fr.jnvui.yavintest.usecases.TicketUseCase
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

        settingsViewModel.tickets.observe(viewLifecycleOwner, {
            val llm = LinearLayoutManager(this.requireContext())
            llm.orientation = LinearLayoutManager.VERTICAL
            ticketsSettingsRecyclerView.layoutManager = llm
            ticketsSettingsRecyclerView.adapter = TicketSettingsAdapter(
                it.toTypedArray(),
                AdapterClickListener({
                    settingsViewModel.updateTicketPrice(it)
                    //close keyboard
                    closeKeyboard(this.requireActivity())
                },
                    {
                        settingsViewModel.updateTicketPrice(it)
                        //close keyboard
                        closeKeyboard(this.requireActivity())
                    })
            )
        })

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    fun closeKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        // To get the correct window token, lets first get the currently focused view
        var view = activity.currentFocus

        // To get the window token when there is no currently focused view, we have a to create a view
        if (view == null) {
            view = View(activity)
        }

        // hide the keyboard
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}