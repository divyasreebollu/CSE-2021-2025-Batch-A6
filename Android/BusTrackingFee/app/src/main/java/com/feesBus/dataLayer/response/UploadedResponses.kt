package com.feesBus.dataLayer.response

import com.google.gson.annotations.SerializedName

data class UploadedResponses(
    @SerializedName("error") var error: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
) {
    data class Data(
        @SerializedName("id") var id: String? = null,
        @SerializedName("userid") var userid: String? = null,
        @SerializedName("uploadDate") var uploadDate: String? = null,
        @SerializedName("image") var image: String? = null,
        @SerializedName("comments") var comments: String? = null,
        @SerializedName("status") var status: String? = null,
    )
}