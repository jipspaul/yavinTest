package fr.jnvui.yavintest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Ticket
import fr.jnvui.yavintest.ui.adapters.TicketAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val players = arrayOf(
            Ticket("Single Journey Ticket", "1,6£"),
            Ticket("Single Journey Ticket", "1,6£"),
            Ticket("Single Journey Ticket", "1,6£")
        ) //TODO remove

        val llm =
            LinearLayoutManager(this.requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        ticketsRecyclerView.setLayoutManager(llm)
        ticketsRecyclerView.adapter = TicketAdapter(players)
    }
}