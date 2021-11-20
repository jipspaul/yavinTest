package fr.jnvui.yavintest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Transaction


class TransactionsAdapter(
    private val dataSet: Array<Transaction>,
) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionDate: TextView
        val transactionPrice: TextView
        val transactionStatus: TextView

        init {
            transactionDate = view.findViewById(R.id.transactionDate)
            transactionPrice = view.findViewById(R.id.transactionPrice)
            transactionStatus = view.findViewById(R.id.transactionStatus)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.transaction_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.transactionDate.text = dataSet[position].date
        (dataSet[position].amount.toString() + " " + dataSet[position].currency).also { viewHolder.transactionPrice.text = it }
        viewHolder.transactionStatus.text = dataSet[position].status
    }

    override fun getItemCount() = dataSet.size
}
