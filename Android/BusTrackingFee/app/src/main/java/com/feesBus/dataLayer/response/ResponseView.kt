package com.feesBus.dataLayer.response

import com.google.gson.annotations.SerializedName

data class ResponseView (
    @SerializedName("channel" ) var channel : Channel?         = Channel(),
    @SerializedName("feeds"   ) var feeds   : ArrayList<Feeds> = arrayListOf()

) {

    data class Channel(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("latitude") var latitude: String? = null,
        @SerializedName("longitude") var longitude: String? = null,
        @SerializedName("field1") var field1: String? = null,
        @SerializedName("field2") var field2: String? = null,
        @SerializedName("created_at") var createdAt: String? = null,
        @SerializedName("updated_at") var updatedAt: String? = null,
        @SerializedName("last_entry_id") var lastEntryId: Int? = null,

        )

    data class Feeds(

        @SerializedName("created_at") var createdAt: String? = null,
        @SerializedName("entry_id") var entryId: Int? = null,
        @SerializedName("field1") var field1: String? = null,
        @SerializedName("field2") var field2: String? = null,

        )
}