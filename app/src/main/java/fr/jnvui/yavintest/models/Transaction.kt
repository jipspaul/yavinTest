package fr.jnvui.yavintest.models

data class Transaction (
    val transactionID: String,
    val date: String,
    val time: String,
    val amount: Long,
    val currency: String,
    val status: String,
    val pan: String,
    val logicalNumber: String,
    val scheme: String
)
