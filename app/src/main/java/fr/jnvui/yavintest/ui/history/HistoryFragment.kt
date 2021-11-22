package fr.jnvui.yavintest.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.jnvui.yavintest.R
import fr.jnvui.yavintest.models.Transaction
import fr.jnvui.yavintest.ui.adapters.TransactionsAdapter
import fr.jnvui.yavintest.ui.helpers.DatePickerHelper
import fr.jnvui.yavintest.usecases.TransactionsUseCase
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.ext.android.inject
import java.util.*

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var firstDatePicker: DatePickerHelper
    private lateinit var secondDatePicker: DatePickerHelper

    private val transactionsUseCase: TransactionsUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProvider(
                this,
                HistoryViewModelFactory(transactionsUseCase)
            ).get(HistoryViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_history, container, false)
        val dateSelectorTextView: TextView = root.findViewById(R.id.dateSelectorTextview)

        historyViewModel.intervalDate.observe(viewLifecycleOwner, Observer {
            dateSelectorTextView.text = it
        })

        firstDatePicker = DatePickerHelper(this.requireContext())
        secondDatePicker = DatePickerHelper(this.requireContext())

        dateSelectorTextView.setOnClickListener {
            selectIntervalDate()
        }

        historyViewModel.transactions.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                showTransactions(it.toTypedArray())
            } else {
                showNoTransactions()
            }
        })

        return root
    }

    private fun showTransactions(transactions: Array<Transaction>) {
        val llm = LinearLayoutManager(this.requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        historyRecyclerView.layoutManager = llm
        historyRecyclerView.adapter = TransactionsAdapter(
            transactions
        )
    }

    private fun showNoTransactions() {
    }

    private fun selectIntervalDate() {

        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        var firstDate = ""
        var secondDate = ""

        firstDatePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                firstDate = "${dayStr}/${monthStr}/${year}"

                //Set min date to first date select
                val minDate = Calendar.getInstance()
                minDate.set(Calendar.HOUR_OF_DAY, 0)
                minDate.set(Calendar.MINUTE, 0)
                minDate.set(Calendar.SECOND, 0)
                secondDatePicker.setMinDate(minDate.timeInMillis)

                //Start new calendar
                secondDatePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
                    override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                        val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                        val mon = month + 1
                        val monthStr = if (mon < 10) "0${mon}" else "${mon}"

                        secondDate = "${dayStr}/${monthStr}/${year}"

                        historyViewModel.changeIntervaleDate(firstDate + " - " + secondDate)

                    }
                })

            }
        })


    }

}