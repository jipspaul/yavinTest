package fr.jnvui.yavintest.ui.adapters

import fr.jnvui.yavintest.models.Ticket

class AdapterClickListener(val clickListener: (ticker: Ticket) -> Unit) {
    fun onClick(ticker: Ticket) = clickListener(ticker)
}