package fr.jnvui.yavintest.usecases

import fr.jnvui.yavintest.models.Transaction
import fr.jnvui.yavintest.network.RequestTransactionBody
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*


class TransactionsUseCase(val requestTransactionBody: RequestTransactionBody) {

    fun getTransactions(startDate: Date, endDate: Date): List<Transaction> {

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

    fun requestTrqnsactions(message: String) {

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
    }

}