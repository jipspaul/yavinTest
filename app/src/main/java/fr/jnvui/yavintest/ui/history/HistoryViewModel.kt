package fr.jnvui.yavintest.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.models.Transaction
import fr.jnvui.yavintest.usecases.TransactionsUseCase
import java.util.*

class HistoryViewModel(private val transactionsUseCase: TransactionsUseCase) : ViewModel() {

    private val _intervalDate = MutableLiveData<String>().apply {
        value = "Click to select date"
    }
    val intervalDate: LiveData<String> = _intervalDate

    private val _transactions = MutableLiveData<List<Transaction>>().apply {
        value = transactionsUseCase.getTransactions(Date(), Date())
    }
    val transactions: LiveData<List<Transaction>> = _transactions


    fun changeIntervaleDate(date: String) {
        _intervalDate.value = date
        _transactions.value = transactionsUseCase.getTransactions(Date(), Date())
    }
}

class HistoryViewModelFactory(
    private val transactionsUseCase: TransactionsUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(transactionsUseCase) as T
    }
}