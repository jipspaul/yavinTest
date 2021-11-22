package fr.jnvui.yavintest.network

import fr.jnvui.yavintest.models.Transaction

data class TransactionsResponse(
    val meta: Meta,
    val data: Data
)

data class Data(
    val transactions: List<Transaction>
)

data class Meta(
    val total: Long,
    val start: Long,
    val count: Long,
    val code: Long,
    val message: String
)
