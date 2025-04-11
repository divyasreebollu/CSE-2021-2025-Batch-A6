package com.feesBus.dataLayer

import com.feesBus.dataLayer.response.CommonResponse
import com.feesBus.dataLayer.response.LoginResponse
import com.feesBus.dataLayer.response.MyDetails
import com.feesBus.dataLayer.response.ResponseView
import com.feesBus.dataLayer.response.UploadedResponses
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @FormUrlEncoded
    @POST("users.php")
    suspend fun users(
        @Field("name") name: String,
        @Field("mail") mail: String,
        @Field("password") password: String,
        @Field("type") type: String,
        @Field("rollNumber") rollNumber: String,
    ): Response<CommonResponse>


    @GET("functions.php")
    suspend fun login(
        @Query("name") name: String,
        @Query("password") password: String,
        @Query("condition") condition: String = "loginPointOflshjsdkfhsdjkf",
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("addPrescriptions.php")
    suspend fun addPrescriptions(
        @Field("userid") userid: String,
        @Field("uploadDate") uploadDate: String,
        @Field("image") image: String,
        @Field("comments") comments: String,
        @Field("status") status: String,
    ): Response<CommonResponse>

    @GET("functions.php")
    suspend fun getData(
        @Query("condition") condition: String = "getMyUploadedTHEPPPPP",
        @Query("id") id: String
    ): Response<UploadedResponses>

    @GET("feeds.json")
    suspend fun getDetailsThings(
        @Query("api_key")api_key:String="XH63OCLCVUJH1T2B",
        @Query("results")results:String="1"
    ):Response<ResponseView>

    @GET("functions.php")
    suspend fun getMyRules(
        @Query("condition") condition: String = "MyWorldMyRules",
        @Query("id") id:String
    ):Response<MyDetails>
}
