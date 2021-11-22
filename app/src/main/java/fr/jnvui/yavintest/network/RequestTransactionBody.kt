package fr.jnvui.yavintest.network

import com.google.gson.annotations.SerializedName

data class RequestTransactionBody(
    @SerializedName("yavin_secret") val yavinSecret: String?
)