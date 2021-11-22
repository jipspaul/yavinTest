package fr.jnvui.yavintest.network

import androidx.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.*

interface TransactionsAPI {
    @POST("/transactions")
    suspend fun getTransactions(@Body requestTransactionBody: RequestTransactionBody): Response<LiveData<TransactionsResponse>>
}