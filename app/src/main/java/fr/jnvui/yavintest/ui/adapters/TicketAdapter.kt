package fr.jnvui.yavintest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Ticket


class TicketAdapter(
    private val dataSet: Array<Ticket>,
    private val onClickListener: AdapterClickListener
) :
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ticketType: TextView
        val ticketPrice: TextView
        val deleteButton: ImageView
        val ticketPriceItem: ConstraintLayout

        init {
            ticketType = view.findViewById(R.id.ticketTypeTextView)
            ticketPrice = view.findViewById(R.id.dayTicketPriceTextView)
            ticketPriceItem = view.findViewById(R.id.ticketPriceItem)
            deleteButton = view.findViewById(R.id.removeItemButton)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ticket_price_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.ticketType.text = dataSet[position].ticketType

        if (dataSet[position].ticketCartCounter > 0) {
            viewHolder.deleteButton.visibility = View.VISIBLE
            (dataSet[position].ticketPrice + "€ x" + dataSet[position].ticketCartCounter).also {
                viewHolder.ticketPrice.text = it
            }
        } else {
            viewHolder.deleteButton.visibility = View.INVISIBLE
            (dataSet[position].ticketPrice + "€").also { viewHolder.ticketPrice.text = it }
        }

        viewHolder.ticketPriceItem.setOnClickListener {
            onClickListener.onClick(dataSet[position])
        }

        viewHolder.deleteButton.setOnClickListener {
            onClickListener.onClickCounterDelete(dataSet[position])
        }

    }

    override fun getItemCount() = dataSet.size

}
