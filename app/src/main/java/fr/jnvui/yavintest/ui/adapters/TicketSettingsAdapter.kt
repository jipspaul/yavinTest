package fr.jnvui.yavintest.ui.adapters

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Ticket

class TicketSettingsAdapter(
    private val dataSet: Array<Ticket>,
    private val onClickListener: AdapterClickListener
) :
    RecyclerView.Adapter<TicketSettingsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ticketType: TextView
        val ticketPrice: EditText
        val editPrice: Button

        init {
            ticketType = view.findViewById(R.id.ticketTypeTextView)
            ticketPrice = view.findViewById(R.id.dayTicketPriceTextView)
            editPrice = view.findViewById(R.id.editButton)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ticket_settings_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.ticketType.text = dataSet[position].ticketType
        viewHolder.ticketPrice.text =
            Editable.Factory.getInstance().newEditable(dataSet[position].ticketPrice)
        viewHolder.editPrice.setOnClickListener {
            //get edittext value and use onclick to send it to viewmodel
            dataSet[position].ticketPrice = viewHolder.ticketPrice.text.toString()
            onClickListener.onClick(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size

}
