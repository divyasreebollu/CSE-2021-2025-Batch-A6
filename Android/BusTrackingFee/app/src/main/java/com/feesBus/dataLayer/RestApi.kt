package com.feesBus.dataLayer

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestApi {
     var BASEURL = "https://wizzie.online/BusTracking/"

    private val okhttp = okhttp3.OkHttpClient.Builder().addInterceptor {
        it.withReadTimeout(10, TimeUnit.MINUTES)
        it.withWriteTimeout(10, TimeUnit.MINUTES)
        it.withConnectTimeout(10, TimeUnit.MINUTES)
        it.proceed(it.request()).newBuilder().build()
    }.build()

    val api: Api? by lazy {
        retrofit2.Retrofit.Builder()
            .client(okhttp)
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }


val api2: Api? by lazy {
        retrofit2.Retrofit.Builder()
            .client(okhttp)
            .baseUrl("https://api.thingspeak.com/channels/2820190/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }



}