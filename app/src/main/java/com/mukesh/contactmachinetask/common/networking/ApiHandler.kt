package com.mukesh.contactmachinetask.common.networking

import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiHandler {

    @FormUrlEncoded
    @POST("sms/json")
    suspend fun sendOtp(
        @FieldMap hashMap: HashMap<String, String>
    ): Response<Any>

}