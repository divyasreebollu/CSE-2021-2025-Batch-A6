package com.feesBus.dataLayer.response

import com.google.gson.annotations.SerializedName

data class MyDetails (
    @SerializedName("error"   ) var error   : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
){
    data class Data(
        @SerializedName("id"         ) var id         : String? = null,
        @SerializedName("busId"      ) var busId      : String? = null,
        @SerializedName("userid"     ) var userid     : String? = null,
        @SerializedName("seatNumber" ) var seatNumber : String? = null,
        @SerializedName("datePoint"  ) var datePoint  : String? = null,
        @SerializedName("name"       ) var name       : String? = null,
        @SerializedName("busNumber"  ) var busNumber  : String? = null,
        @SerializedName("seats"      ) var seats      : String? = null,
        @SerializedName("stops"      ) var stops      : String? = null,
        @SerializedName("fromPlace"  ) var fromPlace  : String? = null,
        @SerializedName("toPlace"    ) var toPlace    : String? = null
    )
}