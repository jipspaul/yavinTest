package fr.jnvui.yavintest.ui.adapters

import fr.jnvui.yavintest.models.Ticket

class AdapterClickListener(
    val clickListener: (ticker: Ticket) -> Unit,
    val clickCounterDelete: (ticker: Ticket) -> Unit
) {
    fun onClick(ticker: Ticket) = clickListener(ticker)
    fun onClickCounterDelete(ticker: Ticket) = clickCounterDelete(ticker)
}