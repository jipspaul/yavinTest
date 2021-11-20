package fr.jnvui.yavintest.usecases

import fr.jnvui.yavintest.models.Transaction

class TransactionsUseCase() {

    fun getTransactions(): List<Transaction> {
        return listOf(
            Transaction(
                "b14fbe01-fd2a-419b-9f4d-6aea4edf514b",
                "2020-01-15",
                "11:19:31",
                500,
                "EUR",
                "DEBIT",
                "DEBIT",
                "DEBIT",
                "DEBIT"
            ),
            Transaction(
                "b14fbe01-fd2a-419b-9f4d-6aea4edf514b",
                "2020-01-15",
                "11:19:31",
                60,
                "EUR",
                "DEBIT",
                "DEBIT",
                "DEBIT",
                "DEBIT"
            ),
            Transaction(
                "b14fbe01-fd2a-419b-9f4d-6aea4edf514b",
                "2020-01-15",
                "11:19:31",
                25,
                "EUR",
                "DEBIT",
                "DEBIT",
                "DEBIT",
                "DEBIT"
            ),
            Transaction(
                "b14fbe01-fd2a-419b-9f4d-6aea4edf514b",
                "2020-01-15",
                "11:19:31",
                50,
                "EUR",
                "DEBIT",
                "DEBIT",
                "DEBIT",
                "DEBIT"
            ),
            Transaction(
                "b14fbe01-fd2a-419b-9f4d-6aea4edf514b",
                "2020-01-15",
                "11:19:31",
                1000,
                "EUR",
                "DEBIT",
                "DEBIT",
                "DEBIT",
                "DEBIT"
            )
        )
    }

}