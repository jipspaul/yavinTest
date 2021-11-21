package fr.jnvui.yavintest.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jnvui.yavintest.models.Transaction
import fr.jnvui.yavintest.usecases.TransactionsUseCase

class HistoryViewModel(private val transactionsUseCase: TransactionsUseCase) : ViewModel() {

    private val _intervalDate = MutableLiveData<String>().apply {
        value = "2020/01/15 - 2020/01/15"
    }
    val intervalDate: LiveData<String> = _intervalDate

    private val _transactions = MutableLiveData<List<Transaction>>().apply {
        value = transactionsUseCase.getTransactions()
    }
    val transactions: LiveData<List<Transaction>> = _transactions


    fun changeIntervaleDate(date: String) {
        _intervalDate.value = date
    }
}

class HistoryViewModelFactory(
    private val transactionsUseCase: TransactionsUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(transactionsUseCase) as T
    }
}