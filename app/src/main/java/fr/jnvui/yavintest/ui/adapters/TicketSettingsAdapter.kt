package fr.jnvui.yavintest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Ticket


class TicketSettingsAdapter(private val dataSet: Array<Ticket>) :
    RecyclerView.Adapter<TicketSettingsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ticketType: TextView
        val ticketPrice: TextView

        init {
            ticketType = view.findViewById(R.id.ticketTypeTextView)
            ticketPrice = view.findViewById(R.id.dayTicketPriceTextView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ticket_settings_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.ticketType.text = dataSet[position].ticketType
        viewHolder.ticketPrice.text = dataSet[position].ticketPrice

    }

    override fun getItemCount() = dataSet.size

}
