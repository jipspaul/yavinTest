package fr.jnvui.yavintest.usecases

import com.google.gson.Gson
import fr.jnvui.yavintest.models.Transaction
import fr.jnvui.yavintest.network.RequestTransactionBody
import fr.jnvui.yavintest.network.RetrofitHelper
import fr.jnvui.yavintest.network.TransactionsAPI
import fr.jnvui.yavintest.network.TransactionsResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*


class TransactionsUseCase(val requestTransactionBody: RequestTransactionBody) {

    fun getTransactions(startDate: Date, endDate: Date): List<Transaction> {

        val transactionApi = RetrofitHelper.getInstance().create(TransactionsAPI::class.java)
        // launching a new coroutine
        GlobalScope.launch {

            val result =
                transactionApi.getTransactions(requestTransactionBody)

            var string =
                requestTrqnsactions("{\"yavin_secret\":\"1s4DMHdqcZg1CnovHt1EYaNkuFe5TeeLV1YehyXLMj1aq2e8kI\"}")
            var transactionsResponse =
                Gson().fromJson(string, TransactionsResponse::class.java)

            string.length
        }

        return emptyList()
    }

    fun requestTrqnsactions(message: String): String {

        val serverURL: String = "https://api.sandbox.yavin.com/api/v1/transactions/"
        val url = URL(serverURL)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.connectTimeout = 300000
        connection.connectTimeout = 300000
        connection.doOutput = true

        val postData: ByteArray = message.toByteArray(StandardCharsets.UTF_8)

        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty("Content-length", postData.size.toString())
        connection.setRequestProperty("Content-Type", "application/json")


        try {
            val outputStream = DataOutputStream(connection.outputStream)
            outputStream.write(postData)
            outputStream.flush()

        } catch (exception: Exception) {

        }

        var br: BufferedReader? = null
        br = if (connection.responseCode in 100..399) {
            BufferedReader(InputStreamReader(connection.inputStream))
        } else {
            BufferedReader(InputStreamReader(connection.errorStream))
        }

        var sb = StringBuilder()
        var output: String?
        while (br.readLine().also { output = it } != null) {
            sb.append(output)
        }

        return sb.toString()
    }

}