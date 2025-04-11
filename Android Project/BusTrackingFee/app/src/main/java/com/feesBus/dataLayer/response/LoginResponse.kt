package com.feesBus.dataLayer.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("error") var error: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
) {
    data class Data(
        @SerializedName("id") var id: String? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("mail") var mail: String? = null,
        @SerializedName("type") var type: String? = null,
    )
}